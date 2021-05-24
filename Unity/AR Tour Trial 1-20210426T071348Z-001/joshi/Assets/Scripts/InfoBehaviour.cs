using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InfoBehaviour : MonoBehaviour
{
	const float SPEED = 6f;
	[SerializeField]
	Transform SecionInfo;

	int i=0;

	Vector3 desiredScale = Vector3.zero;

	internal List<InfoBehaviour> ToList()
	{
		throw new NotImplementedException();
	}

	// Update is called once per frame
	void Update()
    {
		if(i!=3){
		SecionInfo.localScale = Vector3.Scale(SecionInfo.localScale, desiredScale);
		print("UPDATE + "+SecionInfo.localScale.ToString());
		i++;
	}
    }

	public void OpenInfo()
	{

		desiredScale = Vector3.one;
		
		SecionInfo.localScale = Vector3.Scale(SecionInfo.localScale, desiredScale );
		print(SecionInfo.localScale.ToString());
	}
	public void CloseInfo()
	{
		desiredScale = Vector3.zero;
	}

}
