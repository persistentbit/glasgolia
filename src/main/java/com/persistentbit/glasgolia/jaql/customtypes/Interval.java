package com.persistentbit.glasgolia.jaql.customtypes;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import com.persistentbit.core.javacodegen.annotations.DefaultValue;
import com.persistentbit.core.javacodegen.annotations.Generated;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 14/07/17
 */
@CaseClass
@NoBuilder
public class Interval {
	@DefaultValue("0")
	private  final	int	years;
	@DefaultValue("0")
	private  final	int	months;
	@DefaultValue("0")
	private  final	int	days;
	@DefaultValue("0")
	private  final	int	hours;
	@DefaultValue("0")
	private  final	int	minutes;
	@DefaultValue("0")
	private  final	double	seconds;
	
	
	@Generated
	public Interval(@Nullable Integer years, @Nullable Integer months, @Nullable Integer days, @Nullable Integer hours, @Nullable Integer minutes, @Nullable Double seconds){
			this.years = years == null ? 0 : years;
			this.months = months == null ? 0 : months;
			this.days = days == null ? 0 : days;
			this.hours = hours == null ? 0 : hours;
			this.minutes = minutes == null ? 0 : minutes;
			this.seconds = seconds == null ? 0 : seconds;
	}
	@Generated
	public Interval(){
			this(null, null, null, null, null, null);
	}
	/**
	 * Get the value of field {@link #years}.<br>
	 * @return {@link #years}
	 */
	@Generated
	public  int	getYears(){
		return this.years;
	}
	/**
	 * Create a copy of this Interval object with a new value for field {@link #years}.<br>
	 * @param years The new value for field {@link #years}
	 * @return A new instance of {@link Interval}
	 */
	@Generated
	public  Interval	withYears(@Nullable Integer years){
		return new Interval(years, months, days, hours, minutes, seconds);
	}
	/**
	 * Get the value of field {@link #months}.<br>
	 * @return {@link #months}
	 */
	@Generated
	public  int	getMonths(){
		return this.months;
	}
	/**
	 * Create a copy of this Interval object with a new value for field {@link #months}.<br>
	 * @param months The new value for field {@link #months}
	 * @return A new instance of {@link Interval}
	 */
	@Generated
	public  Interval	withMonths(@Nullable Integer months){
		return new Interval(years, months, days, hours, minutes, seconds);
	}
	/**
	 * Get the value of field {@link #days}.<br>
	 * @return {@link #days}
	 */
	@Generated
	public  int	getDays(){
		return this.days;
	}
	/**
	 * Create a copy of this Interval object with a new value for field {@link #days}.<br>
	 * @param days The new value for field {@link #days}
	 * @return A new instance of {@link Interval}
	 */
	@Generated
	public  Interval	withDays(@Nullable Integer days){
		return new Interval(years, months, days, hours, minutes, seconds);
	}
	/**
	 * Get the value of field {@link #hours}.<br>
	 * @return {@link #hours}
	 */
	@Generated
	public  int	getHours(){
		return this.hours;
	}
	/**
	 * Create a copy of this Interval object with a new value for field {@link #hours}.<br>
	 * @param hours The new value for field {@link #hours}
	 * @return A new instance of {@link Interval}
	 */
	@Generated
	public  Interval	withHours(@Nullable Integer hours){
		return new Interval(years, months, days, hours, minutes, seconds);
	}
	/**
	 * Get the value of field {@link #minutes}.<br>
	 * @return {@link #minutes}
	 */
	@Generated
	public  int	getMinutes(){
		return this.minutes;
	}
	/**
	 * Create a copy of this Interval object with a new value for field {@link #minutes}.<br>
	 * @param minutes The new value for field {@link #minutes}
	 * @return A new instance of {@link Interval}
	 */
	@Generated
	public  Interval	withMinutes(@Nullable Integer minutes){
		return new Interval(years, months, days, hours, minutes, seconds);
	}
	/**
	 * Get the value of field {@link #seconds}.<br>
	 * @return {@link #seconds}
	 */
	@Generated
	public  double	getSeconds(){
		return this.seconds;
	}
	/**
	 * Create a copy of this Interval object with a new value for field {@link #seconds}.<br>
	 * @param seconds The new value for field {@link #seconds}
	 * @return A new instance of {@link Interval}
	 */
	@Generated
	public  Interval	withSeconds(@Nullable Double seconds){
		return new Interval(years, months, days, hours, minutes, seconds);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Interval == false) return false;
		Interval obj = (Interval)o;
		if(years!= obj.years) return false;
		if(months!= obj.months) return false;
		if(days!= obj.days) return false;
		if(hours!= obj.hours) return false;
		if(minutes!= obj.minutes) return false;
		if(Double.compare(seconds, obj.seconds) != 0) return false;
		if(seconds!= obj.seconds) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		long temp;
		result = this.years;
		result = 31 * result + this.months;
		result = 31 * result + this.days;
		result = 31 * result + this.hours;
		result = 31 * result + this.minutes;
		temp =  Double.doubleToLongBits(this.seconds);
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Interval[" + 
			"years=" + years + 
			", months=" + months + 
			", days=" + days + 
			", hours=" + hours + 
			", minutes=" + minutes + 
			", seconds=" + seconds + 
			']';
	}
}
