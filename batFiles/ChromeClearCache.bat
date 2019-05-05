
1
2
3
4
@echo off
set ChromeDir=C:\Users\%USERNAME%\AppData\Local\Google\Chrome\User Data
del /q /s /f "%ChromeDir%"
rd /s /q "%ChromeDir%"