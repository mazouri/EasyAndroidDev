package com.oliver.easy.utils;

import android.net.Uri;

/**
 * Created by wangdong on 16-1-6.
 *
 * Utility methods for dealing with URIs.
 */
public class UriUtils {

    // Used for lookup URI that contains an encoded JSON string.
    public static final String LOOKUP_URI_ENCODED = "encoded";
    /** Static helper, not instantiable. */
    private UriUtils() {}

    /** Checks whether two URI are equal, taking care of the case where either is null. */
    public static boolean areEqual(Uri uri1, Uri uri2) {
        if (uri1 == null && uri2 == null) {
            return true;
        }
        if (uri1 == null || uri2 == null) {
            return false;
        }
        return uri1.equals(uri2);
    }

    /** Parses a string into a URI and returns null if the given string is null. */
    public static Uri parseUriOrNull(String uriString) {
        if (uriString == null) {
            return null;
        }
        return Uri.parse(uriString);
    }

    /** Converts a URI into a string, returns null if the given URI is null. */
    public static String uriToString(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    public static boolean isEncodedContactUri(Uri uri) {
        return uri != null && uri.getLastPathSegment().equals(LOOKUP_URI_ENCODED);
    }


    /**
     * Determines if the specified number is actually a URI
     * (i.e. a SIP address) rather than a regular PSTN phone number,
     * based on whether or not the number contains an "@" character.
     *
     * @param number
     * @return true if number contains @
     * @hide
     */
    public static boolean isUriNumber(String number) {
        // Note we allow either "@" or "%40" to indicate a URI, in case
        // the passed-in string is URI-escaped.  (Neither "@" nor "%40"
        // will ever be found in a legal PSTN number.)
        return number != null && (number.contains("@") || number.contains("%40"));
    }
}
