package com.douzone.mysite.web.board;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("view".equals(actionName)) {
			action = new ViewAction();
		} else if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("search".equals(actionName)) {
			action = new SearchAction();
		} else if ("comment".equals(actionName)) {
			action = new CommentAction();
		}
		
		
		
		else {
			action = new ListAction();
		}

		return action;
	}

}
