package cleancode.crawler.api.entity;

import java.io.Serializable;

/**
 * 
 * @author Neda Peyrone
 *
 */
public class LinkItem implements Serializable {

	private static final long serialVersionUID = -8420167688643709206L;

	private String href;
	private String text;
	
	public LinkItem() {
	}
	
	public LinkItem(String href, String text) {
		this.href = href;
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return this.href + "\n\t" + this.text;
	}
}