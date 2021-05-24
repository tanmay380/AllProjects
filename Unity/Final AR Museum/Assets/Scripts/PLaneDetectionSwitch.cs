using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.XR.ARFoundation;
using TMPro;

public class PLaneDetectionSwitch : MonoBehaviour
{

    ARPlaneManager m_ARPlaneManager;

    public TMP_Text buttonText;

    private void Awake()
    {
        m_ARPlaneManager = GetComponent<ARPlaneManager>();
    }

    public void TogglePlaceDetection()
    {
        m_ARPlaneManager.enabled = !m_ARPlaneManager.enabled;

        if (m_ARPlaneManager.enabled)
        {
            SetAllPlanesActiveOrDeactivate(true);
            GetComponent<TapToplace>().enabled = true;

            buttonText.text = "Disable Plane Detection and Hide Existing";
        }
        else
        {
            SetAllPlanesActiveOrDeactivate(false);
            GetComponent<TapToplace>().enabled = false;
            buttonText.text = "Enable Plane Detection and Show Existing";
        }

    }

    void SetAllPlanesActiveOrDeactivate(bool value)
    {

        foreach (var plane in m_ARPlaneManager.trackables)
        {
            plane.gameObject.SetActive(value);
        }

    }

}
