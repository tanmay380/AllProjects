using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Rendering;


public class Portal : MonoBehaviour
{



    public static GameObject device;


    bool wasInFront, inOtherWorld;
    public Material[] materials;
    void Start()
    {
        
        device = GameObject.FindWithTag("MainCamera");
        SetMaterials(false);
    }

    public void SetMaterials(bool fullRender)
    {
        var stencilTest = fullRender ? CompareFunction.NotEqual : CompareFunction.Equal;

        foreach (var mat in materials)
        {
            mat.SetInt("_StencilTest", (int)stencilTest);
        }
    }


    bool getIsInFront()
    {
        Vector3 pos = transform.InverseTransformPoint(device.gameObject.transform.position);

        return pos.z >= 0 ? true : false;
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.transform != device.gameObject.transform)
        {
            SSTools.ShowMessage(other.transform.gameObject.name,
                SSTools.Position.bottom, SSTools.Time.oneSecond);
            return;
        }
        wasInFront = getIsInFront();
    }
    private void OnTriggerStay(Collider other)
    {

        if (other.transform != device.gameObject.transform)
        {
            SSTools.ShowMessage(other.transform.gameObject.name,
            SSTools.Position.bottom, SSTools.Time.oneSecond);
            return;
        }

        bool isinFront = getIsInFront();

        if ((isinFront && !wasInFront) || (wasInFront && !isinFront))
        {
            inOtherWorld = !inOtherWorld;
            SetMaterials(inOtherWorld);
        }
        wasInFront = isinFront;

    }

    private void OnDestroy()
    {
        SetMaterials(true);
    }

    void Update()
    {
    }
}
