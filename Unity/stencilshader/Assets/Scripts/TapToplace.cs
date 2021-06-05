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

    private TextMeshProUGUI textlogger;
    ARPlaneManager m_ARPlaneManager;


    ARPointCloudManager _pointCloudManager;
    private Vector2 touchpositon;

    static List<ARRaycastHit> hits = new List<ARRaycastHit>();


    void Awake()
    {
        arRayCastManager = GetComponent<ARRaycastManager>();

        m_ARPlaneManager = GetComponent<ARPlaneManager>();

        _pointCloudManager = GetComponent<ARPointCloudManager>();


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

    public void SetAllPlanesActiveOrDeactivate(bool value)
    {

        foreach (var plane in m_ARPlaneManager.trackables)
        {
            plane.gameObject.SetActive(value);
        }
        foreach (var Point in _pointCloudManager.trackables) // removes the existing ones.
        {
            Point.gameObject.SetActive(false);
        }
    }

    public void TogglePlaceDetection()
    {
        m_ARPlaneManager.enabled = !m_ARPlaneManager.enabled;

        if (m_ARPlaneManager.enabled)
        {
            SetAllPlanesActiveOrDeactivate(true);

        }
        else
        {
            SetAllPlanesActiveOrDeactivate(false);
        }

    }
    void Update()
    {
        if (!TryGetTouchPosition(out Vector2 touchposition))
            return;
        if (arRayCastManager.Raycast(touchposition, hits, TrackableType.PlaneWithinPolygon))
        {
            var hitPose = hits[0].pose;


            if (spawnedObject == null)
            {
                spawnedObject = Instantiate(gameObject, new Vector3(hitPose.position.x, hitPose.position.y-2, hitPose.position.z), Quaternion.identity); ;
              
                TogglePlaceDetection();
                gaze.infos = FindObjectsOfType<InfoBehaviour>().ToList();
            }
           

        }
    }


}
