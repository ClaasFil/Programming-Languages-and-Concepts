import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Apartment {

    public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}	

}