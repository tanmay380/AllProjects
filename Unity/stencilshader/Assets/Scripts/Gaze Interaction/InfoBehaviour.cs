using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InfoBehaviour : MonoBehaviour
{
    const float SPEED = 6f;

    [SerializeField]
    Transform SectionInfo;
    Vector3 desiredScale = Vector3.zero;

    void Update()
    {
        SectionInfo.localScale = Vector3.Lerp(SectionInfo.localScale, desiredScale, Time.deltaTime * SPEED);
    }

    public void OpenInfos()
    {
        desiredScale = new Vector3(0.5f,0.5f,0.5f);
    }
    public void CloseInfos()
    {
        desiredScale = Vector3.zero;
    }

   

    
}
