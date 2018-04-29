/**
 * Copyright (c) 2018 SyndLoanHub, LLC and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License from within this distribution and at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.syndloanhub.loansum.product.facility.prorated;

import org.joda.beans.ImmutableBean;
import org.joda.beans.gen.BeanDefinition;
import org.joda.beans.gen.PropertyDefinition;

import static com.syndloanhub.loansum.product.facility.LoanContractEventType.BorrowingEvent;

import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import org.joda.beans.MetaBean;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.syndloanhub.loansum.product.facility.LoanContractEventType;

import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.impl.direct.DirectMetaProperty;

/**
 * ProratedBorrowing represents a contract-level event drawing additional funds.
 */
@BeanDefinition
public class ProratedBorrowing implements ProratedLoanContractEvent, ImmutableBean {

  /**
   * Return type BorrowingEvent.
   */
  @Override
  public LoanContractEventType getType() {
    return BorrowingEvent;
  }

  /**
   * The effective date of the event.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate effectiveDate;

  /**
   * The contract event share amount.
   * <p>
   * The event share amount represents the amount of the event, and must be non-negative.
   */
  @PropertyDefinition(validate = "notNull")
  private final CurrencyAmount amount;

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code ProratedBorrowing}.
   * @return the meta-bean, not null
   */
  public static ProratedBorrowing.Meta meta() {
    return ProratedBorrowing.Meta.INSTANCE;
  }

  static {
    MetaBean.register(ProratedBorrowing.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ProratedBorrowing.Builder builder() {
    return new ProratedBorrowing.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected ProratedBorrowing(ProratedBorrowing.Builder builder) {
    JodaBeanUtils.notNull(builder.effectiveDate, "effectiveDate");
    JodaBeanUtils.notNull(builder.amount, "amount");
    this.effectiveDate = builder.effectiveDate;
    this.amount = builder.amount;
  }

  @Override
  public ProratedBorrowing.Meta metaBean() {
    return ProratedBorrowing.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the effective date of the event.
   * @return the value of the property, not null
   */
  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the contract event share amount.
   * <p>
   * The event share amount represents the amount of the event, and must be non-negative.
   * @return the value of the property, not null
   */
  public CurrencyAmount getAmount() {
    return amount;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ProratedBorrowing other = (ProratedBorrowing) obj;
      return JodaBeanUtils.equal(effectiveDate, other.effectiveDate) &&
          JodaBeanUtils.equal(amount, other.amount);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(effectiveDate);
    hash = hash * 31 + JodaBeanUtils.hashCode(amount);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("ProratedBorrowing{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("effectiveDate").append('=').append(JodaBeanUtils.toString(effectiveDate)).append(',').append(' ');
    buf.append("amount").append('=').append(JodaBeanUtils.toString(amount)).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ProratedBorrowing}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code effectiveDate} property.
     */
    private final MetaProperty<LocalDate> _effectiveDate = DirectMetaProperty.ofImmutable(
        this, "effectiveDate", ProratedBorrowing.class, LocalDate.class);
    /**
     * The meta-property for the {@code amount} property.
     */
    private final MetaProperty<CurrencyAmount> _amount = DirectMetaProperty.ofImmutable(
        this, "amount", ProratedBorrowing.class, CurrencyAmount.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "effectiveDate",
        "amount");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          return _effectiveDate;
        case -1413853096:  // amount
          return _amount;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ProratedBorrowing.Builder builder() {
      return new ProratedBorrowing.Builder();
    }

    @Override
    public Class<? extends ProratedBorrowing> beanType() {
      return ProratedBorrowing.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code effectiveDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> effectiveDate() {
      return _effectiveDate;
    }

    /**
     * The meta-property for the {@code amount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyAmount> amount() {
      return _amount;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          return ((ProratedBorrowing) bean).getEffectiveDate();
        case -1413853096:  // amount
          return ((ProratedBorrowing) bean).getAmount();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ProratedBorrowing}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<ProratedBorrowing> {

    private LocalDate effectiveDate;
    private CurrencyAmount amount;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(ProratedBorrowing beanToCopy) {
      this.effectiveDate = beanToCopy.getEffectiveDate();
      this.amount = beanToCopy.getAmount();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          return effectiveDate;
        case -1413853096:  // amount
          return amount;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          this.effectiveDate = (LocalDate) newValue;
          break;
        case -1413853096:  // amount
          this.amount = (CurrencyAmount) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public ProratedBorrowing build() {
      return new ProratedBorrowing(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the effective date of the event.
     * @param effectiveDate  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder effectiveDate(LocalDate effectiveDate) {
      JodaBeanUtils.notNull(effectiveDate, "effectiveDate");
      this.effectiveDate = effectiveDate;
      return this;
    }

    /**
     * Sets the contract event share amount.
     * <p>
     * The event share amount represents the amount of the event, and must be non-negative.
     * @param amount  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder amount(CurrencyAmount amount) {
      JodaBeanUtils.notNull(amount, "amount");
      this.amount = amount;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("ProratedBorrowing.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("effectiveDate").append('=').append(JodaBeanUtils.toString(effectiveDate)).append(',').append(' ');
      buf.append("amount").append('=').append(JodaBeanUtils.toString(amount)).append(',').append(' ');
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}