package com.bom;

import com.agile.api.APIException;
import com.agile.api.IAgileSession;
import com.agile.api.IItem;
import com.agile.api.INode;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.ItemConstants;
import com.agile.px.ActionResult;
import com.agile.px.EventActionResult;
import com.agile.px.IEventAction;
import com.agile.px.IEventInfo;

public class AddBOM implements IEventAction {
	public static final String parent = "P10";
	public static final String child1 = "P21";
	public static final String child2 = "P22";

	@Override
	public EventActionResult doAction(IAgileSession session, INode actionNode,
			IEventInfo request) {

		try {
			String name = null;
			if (name==null) {
				System.out.println("Hi");
			}
			IItem itemParent = createItem(session, parent);
			System.out.println("itemParent12:" + itemParent);
			IItem itemChild1 = createItem(session, child1);
			System.out.println("itemChild:" + itemChild1);
			IItem itemChild2 = createItem(session, child2);
			System.out.println("itemChild2:" + itemChild2);
			ITable bomTable = addBOM(itemParent, itemChild1, itemChild2);
			System.out.println("bomTable:"+bomTable);
		} catch (APIException e) {
			e.printStackTrace();
		}
		ActionResult actionResult = new ActionResult(ActionResult.STRING, "Added to the parent part");
		return new EventActionResult(request, actionResult);
	}

	private static IItem createItem(IAgileSession session, String parent)
			throws APIException {
		IItem item = (IItem) session.createObject(ItemConstants.CLASS_PART,
				parent);
		return item;
	}

	private static ITable addBOM(IItem itemParent, IItem itemChild1,
			IItem itemChild2) throws APIException {
		ITable table = itemParent.getTable(ItemConstants.TABLE_BOM);
		System.out.println("Table in Bom:" + table);
		IRow row1 = table.createRow();
		System.out.println("row1:" + row1);
		String number = (String) itemChild1
				.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
		System.out.println("number:" + number);
		row1.setValue(ItemConstants.ATT_BOM_ITEM_NUMBER, number);
		IRow row2 = table.createRow();
		System.out.println("row2:" + row2);
		String number1 = (String) itemChild2
				.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
		System.out.println("number1:" + number1);
		row2.setValue(ItemConstants.ATT_BOM_ITEM_NUMBER, number1);
		return table;
	}

}
