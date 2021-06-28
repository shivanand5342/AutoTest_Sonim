#include <MsgBoxConstants.au3>

Run("QXDM.exe")
if WinWaitActive("[CLASS:Qt5QWindowIcon]", "" , 20) Then
	MsgBox($MB_OK, "Tutorial", "window activated")
Else
	WinActivate("[CLASS:Qt5QWindowIcon]")

Local $i = 0
While $i <= 10
	sleep(2000)
	WinActivate("[CLASS:Qt5QWindowIcon]")
	Local $title = WinGetTitle ("[active]")
	if StringInStr($title, "QXDM") Then
       ;
	   ExitLoop
	Else
	   ;WinSetState("[CLASS:Qt5QWindowIcon]", "", @SW_MAXIMIZE)

	   Sleep(3000)
	   MsgBox($MB_OK, "Tutorial", (WinGetTitle ("[active]")))
	EndIf
    $i = $i + 1
WEnd
Send("^i")
