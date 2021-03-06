package com.oliver.easy.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.oliver.easy.utils.log.EasyLogHelper;

/**
 * Created by wangdong on 16-1-6.
 *
 * 文字高亮
 * Highlights the text in a text field.
 */
public class TextSpanUtils {

    private int mHighLightColor;

    private Object mTextStyleSpan;

    public TextSpanUtils(int highLightColor) {
        mHighLightColor = highLightColor;
        mTextStyleSpan = getStyleSpan();
    }

    /**
     * 设置高亮颜色
     * @param highLightColor
     */
    public void setHighLightColor(int highLightColor) {
        mHighLightColor = highLightColor;
        mTextStyleSpan = getStyleSpan();
    }

    /**
     * 设置TextView内容及高亮
     * @param view
     * @param text
     * @param start 高亮起始位置
     * @param end 高亮终止位置
     */
    public void setViewText(TextView view, String text, int start, int end) {
        SpannableString ss = new SpannableString(text);
        ss.setSpan(mTextStyleSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置TextView的内容，并高亮第一个前缀prefix的字符串
     *
     * @param view
     *            the view on which to set the text
     * @param text
     *            the string to use as the text
     * @param prefix
     *            the prefix to look for
     */
    public void setPrefixText(TextView view, String text, String prefix) {
        view.setText(applyPrefixHighlight(text, prefix));
    }

    private CharacterStyle getStyleSpan() {
        return new ForegroundColorSpan(mHighLightColor);
    }

    /**
     * Applies highlight span to the text.
     *
     * @param text
     *            Text sequence to be highlighted.
     * @param start
     *            Start position of the highlight sequence.
     * @param end
     *            End position of the highlight sequence.
     */
    public void applyMaskingHighlight(SpannableString text, int start, int end) {
        /** Sets text color of the masked locations to be highlighted. */
        text.setSpan(getStyleSpan(), start, end, 0);
    }

    /**
     * Returns a CharSequence which highlights the given prefix if found in the
     * given text.
     *
     * @param text
     *            the text to which to apply the highlight
     * @param prefix
     *            the prefix to look for
     */
    public SpannableString applyPrefixHighlight(CharSequence text, String prefix) {
        if (prefix == null) {
            return null;
        }

        // Skip non-word characters at the beginning of prefix.
        int prefixStart = 0;
        while (prefixStart < prefix.length()
                && !Character.isLetterOrDigit(prefix.charAt(prefixStart))) {
            prefixStart++;
        }
        final String trimmedPrefix = prefix.substring(prefixStart);

        int index = indexOfWordPrefix(text, trimmedPrefix);
        if (index != -1) {
            final SpannableString result = new SpannableString(text);
            result.setSpan(mTextStyleSpan, index, index
                    + trimmedPrefix.length(),  /* flags */Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return result;
        } else {
            return null;
        }
    }

    /**
     * Finds the index of the first word that starts with the given prefix.
     * <p>
     * If not found, returns -1.
     *
     * @param text
     *            the text in which to search for the prefix
     * @param prefix
     *            the text to find, in upper case letters
     */
    public static int indexOfWordPrefix(CharSequence text, String prefix) {
        if (prefix == null || text == null) {
            return -1;
        }

        int textLength = text.length();
        int prefixLength = prefix.length();

        if (prefixLength == 0 || textLength < prefixLength) {
            return -1;
        }

        int i = 0;
        while (i < textLength) {
            // Skip non-word characters
            while (i < textLength && !Character.isLetterOrDigit(text.charAt(i))) {
                i++;
            }

            if (i + prefixLength > textLength) {
                return -1;
            }

            // Compare the prefixes
            int j;
            for (j = 0; j < prefixLength; j++) {
                if (Character.toUpperCase(text.charAt(i + j)) != prefix
                        .charAt(j)) {
                    break;
                }
            }
            if (j == prefixLength) {
                return i;
            }

            // Skip this word
            while (i < textLength && Character.isLetterOrDigit(text.charAt(i))) {
                i++;
            }
        }

        return -1;
    }
}
