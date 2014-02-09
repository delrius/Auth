package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import ua.kiev.naukma.auth.client.utils.Color;

public class TextColoredCell extends AbstractCell<String> {

    private static ColoredCellTemplates templates = GWT.create(ColoredCellTemplates.class);

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (value == null) {
            return;
        }
        SafeHtml safeValue = SafeHtmlUtils.fromString(value);
        SafeStylesBuilder stylesBuilder = new SafeStylesBuilder();
        SafeStyles styles = SafeStylesUtils.forTrustedColor(Color.getRandomColor());
        SafeStyles stylesBackground = SafeStylesUtils.forTrustedBackgroundColor(Color.getRandomColor());
        stylesBuilder.append(styles);
        stylesBuilder.append(stylesBackground);
        SafeHtml rendered = templates.cell(stylesBuilder.toSafeStyles(), safeValue);
        sb.append(rendered);
    }

    interface ColoredCellTemplates extends SafeHtmlTemplates {
        @SafeHtmlTemplates.Template("<div style=\"{0};\">{1}</div>")
        SafeHtml cell(SafeStyles styles, SafeHtml value);
    }
}
