package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Scheduled implements ScheduledInterface {

	private GregorianCalendar date;
	private boolean completed = false;

	public Scheduled(GregorianCalendar date) {
		this.date = date;
	}

	@Override
	public GregorianCalendar getDate() {
		return date;
	}

	@Override
	public boolean isCompleted() {
		if (!completed && date.compareTo(new GregorianCalendar()) >= 0)
			completed = true;
		return completed;
	}

	@Override
	public String toString() {
		return "Date: " + (new SimpleDateFormat("dd-MM-yyyy").format(getDate().getTime())) + "\tCompleted:"
				+ isCompleted();
	}

}
