using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ParentNull : MonoBehaviour
{
    // Start is called before the first frame update

    public GameObject Arcam;

    void Start()
    {
        this.gameObject.transform.SetParent(null);
        Arcam = GameObject.FindGameObjectWithTag("MainCamera");
        gameObject.transform.eulerAngles = new Vector3(0f, Arcam.transform.eulerAngles.y, 0f);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
