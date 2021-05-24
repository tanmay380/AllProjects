using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class gaze : MonoBehaviour
{
    public static List<InfoBehaviour> infos = new List<InfoBehaviour>();
    float distance = 7f;


    private void Awake()
    {
        infos = FindObjectsOfType<InfoBehaviour>().ToList();
    }


    private void Update()
    {
        if (Physics.Raycast(transform.position, transform.forward, out RaycastHit hit, distance))
        {
            GameObject go = hit.collider.gameObject;
            if (go.CompareTag("hasInfo"))
            {
                OpenInfo(go.GetComponent<InfoBehaviour>());
            }
        }
        else
        {
            CloseAll();
        }

    }

    void OpenInfo(InfoBehaviour desiredInfo)
    {
        foreach (InfoBehaviour info in infos)
        {

            if (info == desiredInfo)
            {
                info.OpenInfos();
            }
            else
            {

                info.CloseInfos();

            }
        }
    }

    void CloseAll()
    {
        foreach (InfoBehaviour info in infos)
        {
            info.CloseInfos();
        }
    }




}
