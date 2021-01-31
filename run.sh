#!/bin/bash
# 放自己的jar｜war包
APP_NAME=nemodata-1.0.jar
# jvm参数
JVM_ARGS='-XX:+AggressiveOpts -Xms2G -Xmx2G -server -XX:+HeapDumpOnOutOfMemoryError'

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh [start|stop|restart|status]"
    exit 1
}

is_exist() {
    pid=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}' `
    if [ -z "${pid}" ]; then
      return 1
    else
      return 0
    fi
}

start() {
   is_exist
   if [ $? -eq "0" ]; then
    # java -jar ${APP_NAME}
     echo "${APP_NAME} is already running. pid=${pid} ."
   else
     nohup java $JVM_ARGS -jar $APP_NAME>> console.log 2>&1 &
   fi
}

stop() {
   is_exist
   if [ $? -eq "0" ]; then
     kill -9 $pid
   else
     echo "${APP_NAME} is not running"
   fi
}

status() {
   is_exist
   if [ $? -eq "0" ]; then
     echo "${APP_NAME} is running. Pid is ${pid}"
   else
     echo "${APP_NAME} is not running."
   fi
}

restart() {
   stop
   start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
   "start")
     start
     ;;
   "stop")
     stop
     ;;
   "status")
     status
     ;;
   "restart")
     restart
     ;;
   *)
     usage
     ;;
esac