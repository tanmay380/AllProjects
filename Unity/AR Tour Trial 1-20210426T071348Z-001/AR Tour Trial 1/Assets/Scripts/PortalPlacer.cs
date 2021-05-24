using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Experimental.XR;
using UnityEngine.XR.ARFoundation;
public class PortalPlacer : MonoBehaviour
{
    ARRaycastManager m_ARraycastmanager;
    
     
    List<ARRaycastHit> raycast_hits = new List<ARRaycastHit>();

    public GameObject PortalPrefab;
    private GameObject spawnedPortal;
    private void Awake()
    {
        m_ARraycastmanager = GetComponent<ARRaycastManager>();
    }
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.touchCount >0)
        {
            Touch touch = Input.GetTouch(0);

            if(m_ARraycastmanager.Raycast(touch.position,raycast_hits,UnityEngine.XR.ARSubsystems.TrackableType.PlaneWithinPolygon))
            {
                Pose pose = raycast_hits[0].pose;

                 if(spawnedPortal == null)
                {
                    
                    spawnedPortal = Instantiate(PortalPrefab, pose.position, Quaternion.Euler(0, 0, 0));


                    var rotationOfPortal = spawnedPortal.transform.rotation.eulerAngles;
                    spawnedPortal.transform.eulerAngles = new Vector3(rotationOfPortal.x, Camera.main.transform.rotation.eulerAngles.y, rotationOfPortal.z);
                }
                 else
                {
                    spawnedPortal.transform.position = pose.position;
                   
                    var rotationOfPortal = spawnedPortal.transform.rotation.eulerAngles;
                    spawnedPortal.transform.eulerAngles = new Vector3(rotationOfPortal.x, Camera.main.transform.rotation.eulerAngles.y, rotationOfPortal.z);
                }


            }

        }
    }
}
