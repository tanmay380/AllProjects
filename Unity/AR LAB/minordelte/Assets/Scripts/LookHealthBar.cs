using System.Collections;
using System.Collections.Generic;
using UnityEngine;


namespace S3
{
    public class LookHealthBar : MonoBehaviour
    {
        // Start is called before the first frame update
        void Start()
        {

        }

        // Update is called once per frame
        void Update()
        {
            transform.LookAt(Camera.main.transform);
        }
    }
}
