# Jarvis

## 简介：

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;我的工作助理--------jarvis


----
## 概览(package)：
|package(包)|Description(描述)|
|---|---|
|operations(作业)|提供日常办公作业场景下提升工作效率的核心功能|
|organization(公司)|办公环境下具体的作业场景|
|desktop(桌面)|提供桌面办公自动化小工具|
|search(搜索)|提供本地搜索服务|

----
### 文档(documentation)：
#### -organization.wzgroup(网筑集团)-

|Class(名称)|Method(方法)|parameter(参数)|Modifier and Type(返回类型)|Description(描述)|
|---|---|---|---|---|
|AmsUserPermissionsList|main(String[] args)|String[] args|static void|获取AMS权限管理系统的数据结构|
|EstateProjectInfo|main(String[] args)|String[] args|static void|房地产项目信息|

----------------------------------------------------------------------------------------------------------------

#### -desktop(桌面)-

|Class(名称)|Method(方法)|parameter(参数)|Modifier and Type(返回类型)|Description(描述)|
|---|---|---|---|---|
|Unzip|main(String[] args)|String[] args|static void|实现解压rar文件|
|searchapp.Createindex|main(String[] args)|String[] args|static void|创建本地文件索引|
|searchapp.LucnenApplication|main(String[] args)|String[] args|static void|本地文件搜索应用|

----------------------------------------------------------------------------------------------------------------

#### -operations(作业)-

|Class(名称)|Method(方法)|parameter(参数)|Modifier and Type(返回类型)|Description(描述)|
|---|---|---|---|---|
|AllFileScan|getDocumentScanner(File file)|file:路径|List|扫描指定目录下的文件列表(含子目录)|
|IsFileExists|setIsFileExists(File file)|file:路径|static boolean|判断指定目录路径下该文件是否存在|
|FileRename|setFileRename(String path,String oldname,String newname)|path:文件所在路径<br> oldname:原文件名称<br> newname:新命名名称<br>|static void|完成指定路径下的文件进行重命名|
|FileScan|getFileName(File file1)|file1:路径|String|扫描指定目录下的文件列表(不含子目录)|
|FilesPath|getAllFilesPath(File dir)|dir:路径|List|扫描指定目录下所有文件路径|
|CharacterStatistics|getCharacterStatistics(String inputStrintCharacter, String inputCharacter)|inputStrintCharacter:文本内容<br>inputCharacter:待统计数量的字符|String|统计某个字符在一段文本中所出现的次数|
|RARExtractor|getUntieRARFile(File sourceRar, File destDir)|sourceRar:rar文件所在路径<br> destDir:指定的解压路径|static void|实现rar压缩文件的解压|
|CreateDataBase|setCreateDataBase(String sql)|sql:SQL语句|void|Sqlite文件型数据库的使用参考:创建数据库|
|InsertData|setInsertData(String strSql) |sql:SQL语句|void|Sqlite文件型数据库的使用参考:向数据库中插入数据|
|ReadExcel|setExcelCell(File file,String sheetName,int intCell)|file:Excel文件路径 <br> sheetName:Excel-Sheet名称 <br> intCell:Excel单元格 |String|读取Excel列数据|

* 查看爬虫robots协议的方法是"在网站URL后面输入：robots.txt "；例如：https://www.jinke.com/robots.txt
----------------------------------------------------------------------------------------------------------------

#### -search(搜索)-

《本地文件搜索服务-专项文档》

#### 概要：

项目整体划分为三层:搜索服务(lucnen)、文件服务(folder)、应用服务(application)。<br>


#### 一、文件服务接口：

##### 1.扫描文件目录

接口：search.folder.ScanFile <br>
方法：getScanFilePath() <br>
类型：List<String> <br>
定义：static <br>
描述：扫描文件目录 <br>
参数： <br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>

</tr>

<tr>
<td>file</td>
<td>File</td>
<td>是</td>
<td>文件对象</td>

</tr>

</table>

返回结果：文件路径List<br>

##### 2.扫描文件名称

接口：folder.ScanFile <br>
方法：getScanFileName() <br>
类型：List<String> <br>
定义：static <br>
描述：扫描文件名称 <br>
参数： <br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>
</tr>

<tr>
<td>file</td>
<td>File</td>
<td>是</td>
<td>文件对象</td>
</tr>

</table>

返回结果：文件名称List<br>



##### 3.扫描文件目录 (废弃)
接口：folder.ScanDirectory <br>
方法：getScanDirectory() <br>
类型：List<String> <br>
定义：static <br>
描述：扫描文件目录 <br>
参数： <br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>
</tr>

<tr>
<td>dir</td>
<td>File</td>
<td>是</td>
<td>文件对象</td>
</tr>

<tr>
<td>level</td>
<td>int</td>
<td>是</td>
<td>目录层级</td>
</tr>

</table>

返回结果：文件路径List<br>

##### 4.删除目录
接口：folder.DeleteDirectory <br>
方法：deleteDir() <br>
类型：boolean <br>
定义：static <br>
描述：删除目录及文件 <br>
参数： <br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>

</tr>

<tr>
<td>file</td>
<td>File</td>
<td>是</td>
<td>文件对象</td>

</tr>

</table>


#### 二、搜索服务接口：

##### 公共参数：INDEX_DIR_PATH

##### 1.创建索引
接口：desktop.searchapp.Createindex <br>
方法：createIndex() <br>
类型：boolean<br>
定义：-- <br>
描述：创建搜索索引文件 <br>
参数： <br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>
</tr>

<tr>
<td>indexDir</td>
<td>String</td>
<td>是</td>
<td>索引地址</td>
</tr>

</table>

返回结果：true或false<br>

##### 2.实现搜索
接口：search.lucnen.SearchBuilder <br>
方法：doSearch()<br>
类型：List<String><br>
定义：static<br>
描述：实现搜索<br>
参数：<br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>
</tr>

<tr>
<td>indexDir</td>
<td>String</td>
<td>是</td>
<td>索引地址</td>
</tr>

<tr>
<td>queryStr</td>
<td>String</td>
<td>是</td>
<td>搜索词</td>
</tr>

</table>

返回结果：list搜索结果 <br>

#### 三、数据转化接口：
##### 1.数组转化
接口：search.converter.DataService <br>
方法：getArray()<br>
类型：String[]<br>
定义：static<br>
描述：list转化数组服务<br>
参数：<br>
<table>
<tr>
<td>名称</td>
<td>类型</td>
<td>必填</td>
<td>描述</td>
</tr>

<tr>
<td>list</td>
<td>List</td>
<td>是</td>
<td>list对象</td>
</tr>

</table>

返回结果：数组地址 <br>

##### 搜索应用：LucnenApplication






















