@set START=%DATE:~10,4%-%DATE:~4,2%-%DATE:~7,2%_%TIME:~0,2%-%TIME:~3,2%-%TIME:~6,2%
@set Folder="All_%START%" 
cd ..\Output
@mkdir %Folder%
cd %Folder%

@echo Monkey started: %START%

@echo Dumping logs
adb logcat -c
start "log_window" /MIN cmd /c "adb logcat -v threadtime -b all>>YOUTUBE_MONKEY.txt 

@adb shell monkey --throttle 1000 -s 1000 --pct-trackball 0 --ignore-crashes --ignore-timeouts --kill-process-after-error --ignore-security-exceptions -v 100000>>YOUTUBE_monkeyeventlog.txt

taskkill /IM cmd.exe /FI "WINDOWTITLE eq log_window"
PAUSE