package com.douzone.mysite.web.guestbook;

import com.douzone.mysite.web.main.MainAction;
import com.douzone.mysite.web.user.JoinAction;
import com.douzone.mysite.web.user.JoinSuccessAction;
import com.douzone.mysite.web.user.JoinformAction;
import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

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
