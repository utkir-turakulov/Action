@echo off 
start/min "cmd" java -jar selenium-server-standalone-2.53.0.jar -role hub -port 4444

start/min "cmd" java -jar selenium-server-standalone-2.53.0.jar -role webdriver -hub http://localhost:4444/grid/register -port 5555 -browser browserName=chrome -Dwebdriver.chrome.driver=chromedriver.exe

start/min "cmd" java -jar selenium-server-standalone-2.53.0.jar -role webdriver -hub http://localhost:4444/grid/register

