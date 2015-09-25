# Description #
A simple log wrapper 4 Android. It wrapps all settings related to logging, including file generation. After the first usage it will create a log file such as log-2012-02-22.txt

# Installation #

Just download [lw4a.jar](http://code.google.com/p/lw4a/downloads/detail?name=lw4a.jar) and put inside **libs** folder

# Example #
```
//setting lw4a
LogW.LOGS_FOLDER_NAME = "my_app_folder";//folder where the logs at
LogW.TAG = "my_app_tag";

//usage
LogW.info(MyClass.class, "my text to log");
LogW.error(MyClass.class, "my text to log");
LogW.warn(MyClass.class, "my text to log");
```