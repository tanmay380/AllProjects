using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class gazze : MonoBehaviour
{
    
     public static List<Info> infoList = new List<Info>();
    private float raycasthotdistace = 3.5f;



    void Update()
    {
        if (Physics.Raycast(transform.position ,transform.forward, out RaycastHit hit, raycasthotdistace))
        {
            GameObject go = hit.collider.gameObject;
           // Debug.LogError("Value of hut is" + hit.collider.gameObject.name );
            if (go.CompareTag("HitInfo"))
            {
               // Debug.LogError("FOUND HAS INFO GAMEOBJECT");
                if (infoList==null)
                {
                    return;
                }
                OpeningInfo(go.GetComponent<Info>());
            }
        }
        else
        {
            closeAll();
        }
    }


    void OpeningInfo(Info desiredInfo)
    {
        foreach (Info info in infoList)
        {
            if (info == desiredInfo)
            {
                //Debug.LogError("Opened anumation of:- " + info);
                info.OpenInfo();
            }
            else
            {

                //Debug.LogError("Closed anumation of:- " + info);
                info.CloseInfo();
            }
        }
    }

    void closeAll()
    {
        foreach (Info item in infoList)
        {
            item.CloseInfo();
        }

    }

}
