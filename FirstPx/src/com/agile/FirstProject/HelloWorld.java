package com.agile.FirstProject;
import com.agile.api.*;
import com.agile.px.ActionResult;
import com.agile.px.ICustomAction;
public class HelloWorld implements ICustomAction
{
  public ActionResult doAction(IAgileSession session, INode node, IDataObject obj)
  {
	
	  //ActionResult actionResult = null;
	  
	   return new ActionResult(ActionResult.STRING,"Hello World");
	  
  }
}
