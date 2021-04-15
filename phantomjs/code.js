//phantomjs 使用的爬虫js

system = require('system')
address = system.args[1];
var page = require('webpage').create();
var url = address;
page.open(url, function (status) {
    //Page is loaded!
    if (status !== 'success') {
        console.log('Unable to post!');
    } else {
            window.setTimeout(function () {
              //page.render("test1.png");  //截图
              console.log(page.content);
              phantom.exit();
          }, 10000);
    }
});
