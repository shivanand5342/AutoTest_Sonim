#include <MsgBoxConstants.au3>

;Run("QXDM.exe")

;WinWaitActive("[CLASS:Qt5QWindowIcon]", "" , 10)

Local $i = 0
While $i <= 10
	sleep(2000)
	WinActivate("[CLASS:Qt5QWindowIcon]")
	WinSetState("[CLASS:Qt5QWindowIcon]","",@SW_MAXIMIZE)
	Local $title = WinGetTitle ("[active]")
	if StringInStr($title, "QXDM") Then
		ExitLoop
	EndIf
    $i = $i + 1
WEnd
Send("^i")
WinWaitActive("Save Item Store (Cancel To Discard)?", "" , 10)
Sleep(3000)
ControlFocus("Save Item Store (Cancel To Discard)?","","Edit1")
;ControlSetText("[CLASS:Edit]","","Edit1",$CmdLine[1])
ControlSetText("Save Item Store (Cancel To Discard)?","","Edit1","log")
Send("{TAB}")
sleep(3000)
ControlClick("Save Item Store (Cancel To Discard)?","&save","")

;sleep(5000)
;Send("{ENTER}")
sleep(2000)
WinSetState("[CLASS:Qt5QWindowIcon]","",@SW_MINIMIZE)
