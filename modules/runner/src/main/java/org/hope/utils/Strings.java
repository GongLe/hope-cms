package org.hope.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * String Utils
 * User: Gongle
 * Date: 13-7-13
 * <p>Operations on {@link java.lang.String} that are
 * {@code null} safe.</p>
 * <p/>
 * <ul>
 * <li><b>IsEmpty/IsBlank</b>
 * - checks if a String contains text</li>
 * <li><b>Trim/Strip</b>
 * - removes leading and trailing whitespace</li>
 * <li><b>Equals</b>
 * - compares two strings null-safe</li>
 * </ul>
 * <p/>
 * <p>The {@code Strings} class defines certain words related to
 * String handling.</p>
 * <p/>
 * <ul>
 * <li>null - {@code null}</li>
 * <li>empty - a zero-length string ({@code ""})</li>
 * <li>space - the space character ({@code ' '}, char 32)</li>
 * <li>whitespace - the characters defined by {@link Character#isWhitespace(char)}</li>
 * <li>trim - the characters &lt;= 32 as in {@link String#trim()}</li>
 * </ul>
 * <p/>
 * <p>#ThreadSafe#</p>
 *
 * @see java.lang.String
 */
@SuppressWarnings("unchecked")
public class Strings extends StringUtils{

    /**
     * Private default constructor.
     */
    private Strings() {
    }

    /**
     * Represents a failed index search.
     */
    public static final int INDEX_NOT_FOUND = -1;
    /**
     * Line separator.
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Maximum length of local part of a valid email address.
     */
    private static final int MAX_EMAIL_LENGTH_LOCAL = 64;

    /**
     * Maximum length of domain part of a valid email address.
     */
    private static final int MAX_EMAIL_LENGTH_DOMAIN = 255;

    /**
     * Maximum length of a valid email address.
     */
    private static final int MAX_EMAIL_LENGTH = 256;

    /**
     * Email pattern.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    /**
     * Converts the specified string into a string list line by line.
     *
     * @param string the specified string
     * @return a list of string lines, returns {@code null} if the specified
     *         string is {@code null}
     * @throws IOException io exception
     */
    public static List<String> toLines(final String string) throws IOException {
        if (null == string) {
            return null;
        }

        final BufferedReader bufferedReader = new BufferedReader(new StringReader(string));
        final List<String> ret = new ArrayList<String>();

        try {
            String line = bufferedReader.readLine();

            while (null != line) {
                ret.add(line);

                line = bufferedReader.readLine();
            }
        } finally {
            bufferedReader.close();
        }

        return ret;
    }

    /**
     * Checks whether the specified string is numeric.
     *
     * @param string the specified string
     * @return {@code true} if the specified string is numeric, returns
     *         returns {@code false} otherwise
     */
    public static boolean isNumeric(final String string) {
        if (isBlank(string)) {
            return false;
        }

        final Pattern pattern = Pattern.compile("[0-9]*");
        final Matcher matcher = pattern.matcher(string);

        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether the specified string is a valid email address.
     *
     * @param string the specified string
     * @return {@code true} if the specified string is a valid email address,
     *         returns {@code false} otherwise
     */
    public static boolean isEmail(final String string) {
        if (isBlank(string)) {
            return false;
        }

        if (MAX_EMAIL_LENGTH < string.length()) {
            return false;
        }

        final String[] parts = string.split("@");

        if (2 != parts.length) {
            return false;
        }

        final String local = parts[0];

        if (MAX_EMAIL_LENGTH_LOCAL < local.length()) {
            return false;
        }

        final String domain = parts[1];

        if (MAX_EMAIL_LENGTH_DOMAIN < domain.length()) {
            return false;
        }

        return EMAIL_PATTERN.matcher(string).matches();
    }


}
