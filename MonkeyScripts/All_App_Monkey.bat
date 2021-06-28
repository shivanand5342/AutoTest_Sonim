@set START=%DATE:~10,4%-%DATE:~4,2%-%DATE:~7,2%_%TIME:~0,2%-%TIME:~3,2%-%TIME:~6,2%
@set Folder="All_%START%" 
cd ..\Output
@mkdir %Folder%
cd %Folder%

@echo Monkey started: %START%

@echo Dumping logs....... 
adb remount 
@echo duming logcat logs.... 
adb shell logcat -v time -d -b radio >logcat_ril.txt
adb shell logcat -v time -d -b radio -s AT >logcat_at.txt
adb shell logcat -v time -d >logcat.txt

@echo pulling application data files 
adb pull /data/data/ C:\Monkey\data/

@adb shell monkey --throttle 1000 -s 1000 --pct-trackball 0 --ignore-crashes --ignore-timeouts --kill-process-after-error --ignore-security-exceptions -v 100000>> ./%Folder%/monkey/monkeyeventlog.txt

@set END=%DATE:~10,4%-%DATE:~4,2%-%DATE:~7,2%_%TIME:~0,2%-%TIME:~3,2%-%TIME:~6,2% @echo Monkey terminated: %END%


@echo pulling ANR dump.... 
adb pull /data/anr C:\Monkey\anr/

@echo Pulling dontpanic logs.... 
adb pull /data/dontpanic C:\Monkey/dontpanic/apanic_console.txt 

@echo Pulling Tombstones.... 
adb pull /data/tombstones C:\Monkey\tombstones/

@echo Pulling dropbox.... 
adb pull /data/system/dropbox C:\Monkey\dropbox/

@echo Clearing old logs....... 
@adb shell "rm /data/dontpanic/*"
@adb shell "rm /data/anr/*"
@adb shell "rm /data/system/dropbox/*"
@adb shell "rm /data/tombstones/*"
pause
