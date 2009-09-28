#include "stdafx.h"
#include "PrinterCheck.h"

JNIEXPORT jint JNICALL Java_PrinterCheck_chekcInstall
(JNIEnv *a, jobject b){
	return 0;
}

/*
 * Class:     PrinterCheck
 * Method:    showInstall
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_PrinterCheck_showInstall
  (JNIEnv *a, jobject b, jint c){
	//MessageBox(NULL,_T("²âÊÔ"),_T("²âÊÔ"),MB_OK);
	HRESULT hResult;
	LPPRINTDLGEX pPDX = NULL;
	LPPRINTPAGERANGE pPageRanges = NULL;

	// Allocate the PRINTDLGEX structure.

	pPDX = (LPPRINTDLGEX)GlobalAlloc(GPTR, sizeof(PRINTDLGEX));
	if (!pPDX)
		exit(0);

	// Allocate an array of PRINTPAGERANGE structures.

	pPageRanges = (LPPRINTPAGERANGE) GlobalAlloc(GPTR, 
					   10 * sizeof(PRINTPAGERANGE));
	if (!pPageRanges)
		exit(0);

	//  Initialize the PRINTDLGEX structure.
	HWND hwnd = (HWND)c;
	pPDX->lStructSize = sizeof(PRINTDLGEX);
	pPDX->hwndOwner = hwnd;
	pPDX->hDevMode = NULL;
	pPDX->hDevNames = NULL;
	pPDX->hDC = NULL;
	pPDX->Flags = PD_RETURNDC | PD_COLLATE;
	pPDX->Flags2 = 0;
	pPDX->ExclusionFlags = 0;
	pPDX->nPageRanges = 0;
	pPDX->nMaxPageRanges = 10;
	pPDX->lpPageRanges = pPageRanges;
	pPDX->nMinPage = 1;
	pPDX->nMaxPage = 1000;
	pPDX->nCopies = 1;
	pPDX->hInstance = 0;
	pPDX->lpPrintTemplateName = NULL;
	pPDX->lpCallback = NULL;
	pPDX->nPropertyPages = 0;
	pPDX->lphPropertyPages = NULL;
	pPDX->nStartPage = START_PAGE_GENERAL;
	pPDX->dwResultAction = 0;

	//  Invoke the Print property sheet.

	hResult = PrintDlgEx(pPDX);

	if ( (hResult == S_OK) &&
			   pPDX->dwResultAction == PD_RESULT_PRINT) {

		// User clicked the Print button, so
		// use the DC and other information returned in the 
		// PRINTDLGEX structure to print the document

	}

	if (pPDX->hDC != NULL) 
		DeleteDC(pPDX->hDC);
	if (pPDX->hDevMode != NULL) 
		GlobalFree(pPDX->hDevMode); 
	if (pPDX->hDevNames != NULL) 
		GlobalFree(pPDX->hDevNames); 
	//MessageBox(NULL,_T("²âÊÔ"),_T("²âÊÔ"),MB_OK);

}

HMODULE _hAWT = 0;

JNIEXPORT jint JNICALL Java_PrinterCheck_getWindowHandle
  (JNIEnv * env, jobject cls, jobject comp)
{
	//MessageBox(NULL,_T("ok"),_T("ÒÑ¾­½øÈë"),MB_OK);
    HWND hWnd = 0;
    typedef jboolean (JNICALL *PJAWT_GETAWT)(JNIEnv*, JAWT*);
    JAWT awt;
    JAWT_DrawingSurface* ds;
    JAWT_DrawingSurfaceInfo* dsi;
    JAWT_Win32DrawingSurfaceInfo* dsi_win;
    jboolean result;
    jint lock;

    //Load AWT Library
	if(!_hAWT){
        //for Java 1.4
        _hAWT = LoadLibrary(_T("jawt.dll"));
		//MessageBox(NULL,_T("ok"),_T("1.4"),MB_OK);
	}
	if(!_hAWT){
        //for Java 1.3
        _hAWT = LoadLibrary(_T("awt.dll"));
		//MessageBox(NULL,_T("ok"),_T("1.3"),MB_OK);
	}
    if(_hAWT)
    {
        PJAWT_GETAWT JAWT_GetAWT = (PJAWT_GETAWT)GetProcAddress(_hAWT, "_JAWT_GetAWT@8");
        if(JAWT_GetAWT)
        {
            awt.version = JAWT_VERSION_1_4; // Init here with JAWT_VERSION_1_3 or JAWT_VERSION_1_4
            //Get AWT API Interface
            result = JAWT_GetAWT(env, &awt);
            if(result != JNI_FALSE)
            {
                ds = awt.GetDrawingSurface(env, comp);
                if(ds != NULL)
                {
                    lock = ds->Lock(ds);
                    if((lock & JAWT_LOCK_ERROR) == 0)
                    {
                        dsi = ds->GetDrawingSurfaceInfo(ds);
                        if(dsi)
                        {
                            dsi_win = (JAWT_Win32DrawingSurfaceInfo*)dsi->platformInfo;
                            if(dsi_win)
                            {
                                hWnd = dsi_win->hwnd;
                            }
                                                else {
                                                        hWnd = (HWND) -1;
                                                }
                            ds->FreeDrawingSurfaceInfo(dsi);
                        }
                                        else {
                                                hWnd = (HWND) -2;
                                        }
                        ds->Unlock(ds);
                    }
                                else {
                                        hWnd = (HWND) -3;
                                }
                    awt.FreeDrawingSurface(ds);
                }
                        else {
                                hWnd = (HWND) -4;
                        }
            }
                else {
                        hWnd = (HWND) -5;
                }
        }
        else {
                hWnd = (HWND) -6;
        }
    }
    else {
        hWnd = (HWND) -7;
    }
    return (jint)hWnd;

}
