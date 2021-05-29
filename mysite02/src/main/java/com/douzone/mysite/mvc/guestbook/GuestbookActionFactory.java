package com.douzone.mysite.mvc.guestbook;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;
import com.douzone.mysite.mvc.main.MainAction;
import com.douzone.mysite.mvc.user.JoinAction;
import com.douzone.mysite.mvc.user.JoinSuccessAction;
import com.douzone.mysite.mvc.user.JoinformAction;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("guestbook".equals(actionName)) {
			action = new IndexAction();
		} else if("insert".equals(actionName)) {
			action = new AddAction();
		}else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new IndexAction();
		}

		return action;
	}

}
