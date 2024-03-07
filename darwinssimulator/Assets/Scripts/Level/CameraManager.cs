using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraManager : MonoBehaviour
{

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.M))
        {
            DisableAllCamerasExceptMainCamera();
        }
    }
    private void DisableAllCamerasExceptMainCamera()
    {
        // R�cup�rer toutes les cam�ras actives dans la sc�ne
        Camera[] allCameras = FindObjectsOfType<Camera>();

        foreach (Camera camera in allCameras)
        {
            if (camera != Camera.main)
            {
                // D�sactiver la cam�ra si elle n'est pas la cam�ra principale
                camera.enabled = false;
            }
            else
            {
                camera.enabled = true;
            }
        }
    }
}
