using System.Collections;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.XR.ARFoundation;
using UnityEngine.XR.ARSubsystems;


[RequireComponent(typeof(ARRaycastManager))]
[RequireComponent(typeof(ARPlaneManager))]
public class TapToplace : MonoBehaviour
{
    public GameObject gameObject;

    private GameObject spawnedObject;
    private ARRaycastManager arRayCastManager;

    ARPlaneManager m_ARPlaneManager;


    private Vector2 touchpositon;

    static List<ARRaycastHit> hits = new List<ARRaycastHit>();

    void Awake()
    {
        arRayCastManager = GetComponent<ARRaycastManager>();

        m_ARPlaneManager = GetComponent<ARPlaneManager>();

    }

    bool TryGetTouchPosition(out Vector2 touchposition)
    {
        if (Input.touchCount > 0)
        {
            touchposition = Input.GetTouch(0).position;
            return true;
        }

        touchposition = default;
        return false;

    }
    public void TogglePlaceDetection()
    {
        m_ARPlaneManager.enabled = !m_ARPlaneManager.enabled;

        if (m_ARPlaneManager.enabled)
        {

            SetAllPlanesActiveOrDeactivate(true);
            GetComponent<TapToplace>().enabled = true;
            SSTools.ShowMessage(m_ARPlaneManager.enabled.ToString()+ "ye awal", SSTools.Position.bottom, SSTools.Time.oneSecond);


        }
        else
        {
            SetAllPlanesActiveOrDeactivate(false);
            GetComponent<TapToplace>().enabled = false;
            SSTools.ShowMessage(m_ARPlaneManager.enabled.ToString(), SSTools.Position.bottom, SSTools.Time.oneSecond);
        }
    }
    void SetAllPlanesActiveOrDeactivate(bool value)
    {

        foreach (var plane in m_ARPlaneManager.trackables)
        {
            plane.gameObject.SetActive(value);
        }

    }

    void Update()
    {
        if (!TryGetTouchPosition(out Vector2 touchposition))
            return;
        if (arRayCastManager.Raycast(touchposition, hits, TrackableType.PlaneWithinPolygon))
        {
            var hitPose = hits[0].pose;
            //SSTools.ShowMessage(hitPose.position.ToString()+ "roation" + hitPose.rotation.ToString(), SSTools.Position.bottom, SSTools.Time.oneSecond);

            if (spawnedObject == null)
            {
                spawnedObject = Instantiate(gameObject, hitPose.position, hitPose.rotation);
                gazze.infoList = FindObjectsOfType<Info>().ToList();
            }
            TogglePlaceDetection();
        }
    }


}
