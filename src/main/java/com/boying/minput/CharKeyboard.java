package com.boying.minput;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.EditorInfo;

public class CharKeyboard extends Keyboard {
	private Key mEnterKey = null;

	public CharKeyboard(Context context, int xmlLayoutResId) {
		super(context, xmlLayoutResId);
	}

	public CharKeyboard(Context context, int xmlLayoutResId, int modeId) {
		super(context, xmlLayoutResId, modeId);
	}

	public CharKeyboard(Context context, int xmlLayoutResId, int modeId,
			int width, int height) {
		super(context, xmlLayoutResId, modeId, width, height);
	}

	public CharKeyboard(Context context, int layoutTemplateResId,
			CharSequence characters, int columns, int horizontalPadding) {
		super(context, layoutTemplateResId, characters, columns,
				horizontalPadding);
	}

	@Override
	protected Key createKeyFromXml(Resources res, Row parent, int x, int y,
			XmlResourceParser parser) {
		Key key = new KeyCharKey(res, parent, x, y, parser);
		if (key.codes[0] == 10) {
			mEnterKey = key;
		}
		return key;
	}

	void setImeOptions(Resources res, int options) {
		if (mEnterKey == null) {
			return;
		}

		switch (options
				& (EditorInfo.IME_MASK_ACTION | EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
		case EditorInfo.IME_ACTION_GO:
			mEnterKey.iconPreview = null;
			mEnterKey.icon = null;
			mEnterKey.label = res.getText(R.string.label_go_key);
			break;
		case EditorInfo.IME_ACTION_NEXT:
			mEnterKey.iconPreview = null;
			mEnterKey.icon = null;
			mEnterKey.label = res.getText(R.string.label_next_key);
			break;
		case EditorInfo.IME_ACTION_SEND:
			mEnterKey.iconPreview = null;
			mEnterKey.icon = null;
			mEnterKey.label = res.getText(R.string.label_send_key);
			break;
		case EditorInfo.IME_ACTION_SEARCH:
			mEnterKey.icon = res.getDrawable(R.drawable.sym_keyboard_search);
			mEnterKey.label = null;
			break;
		default:
			mEnterKey.icon = res.getDrawable(R.drawable.sym_keyboard_return);
			mEnterKey.label = null;
			break;
		}
	}

	static class KeyCharKey extends Key {

		public KeyCharKey(Resources res, Row parent, int x, int y,
				XmlResourceParser parser) {
			super(res, parent, x, y, parser);
		}

		@Override
		public boolean isInside(int x, int y) {
			return super.isInside(x, codes[0] == KEYCODE_CANCEL ? y - 10 : y);
		}
	}

}
