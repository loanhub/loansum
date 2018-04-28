/**
 * Copyright (c) 2018 SyndLoanHub, LLC and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License from within this distribution and at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.syndloanhub.loansum.product.facility;

import org.joda.beans.ImmutableBean;

import static com.syndloanhub.loansum.product.facility.Helper.tsget;
import static com.syndloanhub.loansum.product.facility.FacilityEventType.CommitmentAdjustmentEvent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.opengamma.strata.product.ProductTrade;
import com.opengamma.strata.product.TradeInfo;
import com.syndloanhub.loansum.product.facility.prorated.ProratedCommitmentAdjustment;
import com.syndloanhub.loansum.product.facility.prorated.ProratedLoanEvent;

import org.joda.beans.Bean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.MetaBean;
import org.joda.beans.gen.BeanDefinition;
import org.joda.beans.gen.ImmutableDefaults;
import org.joda.beans.gen.PropertyDefinition;

/**
 * An event acting on a loan facility.
 */
@BeanDefinition
public final class CommitmentAdjustment implements FacilityEvent, ImmutableBean {

  /**
   * Prorate a global commitment adjustment into a share based on a trade.
   */
  @Override
  public ProratedLoanEvent prorate(ProductTrade trade) {
    assert (trade instanceof LoanTrade);

    LoanTrade loanTrade = (LoanTrade) trade;
    TradeInfo info = trade.getInfo();
    LocalDate tradeDate = info.getTradeDate().get();
    double share = 0;

    if (!effectiveDate.isBefore(tradeDate))
      share = amount.getAmount() * tsget(loanTrade.getPctShare(), effectiveDate);

    return ProratedCommitmentAdjustment.builder()
        .effectiveDate(effectiveDate)
        .amount(CurrencyAmount.of(amount.getCurrency(), share))
        .pik(pik)
        .refusalAllowed(refusalAllowed)
        .build();
  }

  /**
   * Return type of facility event CommitmentAdjustmentEvent
   */
  @Override
  public FacilityEventType getType() {
    return CommitmentAdjustmentEvent;
  }

  /**
   * The effective date of the event.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate effectiveDate;

  /**
   * The contract event global amount.
   */
  @PropertyDefinition(validate = "")
  private final CurrencyAmount amount;

  /**
   * Flag indicating that this adjustment represents a capitalization from a PIK accrual.
   */
  @PropertyDefinition(validate = "")
  private final boolean pik;

  /**
   * If true, indicates a non-prorata adjustment, e.g. new lender.
   */
  @PropertyDefinition(validate = "")
  private final boolean refusalAllowed;

  /**
   * Default values: not a PIK and non-prorata.
   * 
   * @param builder
   */
  @ImmutableDefaults
  private static void applyDefaults(Builder builder) {
    builder
        .pik(false)
        .refusalAllowed(true);
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code CommitmentAdjustment}.
   * @return the meta-bean, not null
   */
  public static CommitmentAdjustment.Meta meta() {
    return CommitmentAdjustment.Meta.INSTANCE;
  }

  static {
    MetaBean.register(CommitmentAdjustment.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static CommitmentAdjustment.Builder builder() {
    return new CommitmentAdjustment.Builder();
  }

  private CommitmentAdjustment(
      LocalDate effectiveDate,
      CurrencyAmount amount,
      boolean pik,
      boolean refusalAllowed) {
    JodaBeanUtils.notNull(effectiveDate, "effectiveDate");
    this.effectiveDate = effectiveDate;
    this.amount = amount;
    this.pik = pik;
    this.refusalAllowed = refusalAllowed;
  }

  @Override
  public CommitmentAdjustment.Meta metaBean() {
    return CommitmentAdjustment.Meta.INSTANCE;
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
   * Gets the contract event global amount.
   * @return the value of the property
   */
  public CurrencyAmount getAmount() {
    return amount;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets flag indicating that this adjustment represents a capitalization from a PIK accrual.
   * @return the value of the property
   */
  public boolean isPik() {
    return pik;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets if true, indicates a non-prorata adjustment, e.g. new lender.
   * @return the value of the property
   */
  public boolean isRefusalAllowed() {
    return refusalAllowed;
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
      CommitmentAdjustment other = (CommitmentAdjustment) obj;
      return JodaBeanUtils.equal(effectiveDate, other.effectiveDate) &&
          JodaBeanUtils.equal(amount, other.amount) &&
          (pik == other.pik) &&
          (refusalAllowed == other.refusalAllowed);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(effectiveDate);
    hash = hash * 31 + JodaBeanUtils.hashCode(amount);
    hash = hash * 31 + JodaBeanUtils.hashCode(pik);
    hash = hash * 31 + JodaBeanUtils.hashCode(refusalAllowed);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("CommitmentAdjustment{");
    buf.append("effectiveDate").append('=').append(effectiveDate).append(',').append(' ');
    buf.append("amount").append('=').append(amount).append(',').append(' ');
    buf.append("pik").append('=').append(pik).append(',').append(' ');
    buf.append("refusalAllowed").append('=').append(JodaBeanUtils.toString(refusalAllowed));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CommitmentAdjustment}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code effectiveDate} property.
     */
    private final MetaProperty<LocalDate> _effectiveDate = DirectMetaProperty.ofImmutable(
        this, "effectiveDate", CommitmentAdjustment.class, LocalDate.class);
    /**
     * The meta-property for the {@code amount} property.
     */
    private final MetaProperty<CurrencyAmount> _amount = DirectMetaProperty.ofImmutable(
        this, "amount", CommitmentAdjustment.class, CurrencyAmount.class);
    /**
     * The meta-property for the {@code pik} property.
     */
    private final MetaProperty<Boolean> _pik = DirectMetaProperty.ofImmutable(
        this, "pik", CommitmentAdjustment.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code refusalAllowed} property.
     */
    private final MetaProperty<Boolean> _refusalAllowed = DirectMetaProperty.ofImmutable(
        this, "refusalAllowed", CommitmentAdjustment.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "effectiveDate",
        "amount",
        "pik",
        "refusalAllowed");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          return _effectiveDate;
        case -1413853096:  // amount
          return _amount;
        case 110994:  // pik
          return _pik;
        case -1231792212:  // refusalAllowed
          return _refusalAllowed;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public CommitmentAdjustment.Builder builder() {
      return new CommitmentAdjustment.Builder();
    }

    @Override
    public Class<? extends CommitmentAdjustment> beanType() {
      return CommitmentAdjustment.class;
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
    public MetaProperty<LocalDate> effectiveDate() {
      return _effectiveDate;
    }

    /**
     * The meta-property for the {@code amount} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CurrencyAmount> amount() {
      return _amount;
    }

    /**
     * The meta-property for the {@code pik} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> pik() {
      return _pik;
    }

    /**
     * The meta-property for the {@code refusalAllowed} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> refusalAllowed() {
      return _refusalAllowed;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          return ((CommitmentAdjustment) bean).getEffectiveDate();
        case -1413853096:  // amount
          return ((CommitmentAdjustment) bean).getAmount();
        case 110994:  // pik
          return ((CommitmentAdjustment) bean).isPik();
        case -1231792212:  // refusalAllowed
          return ((CommitmentAdjustment) bean).isRefusalAllowed();
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
   * The bean-builder for {@code CommitmentAdjustment}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<CommitmentAdjustment> {

    private LocalDate effectiveDate;
    private CurrencyAmount amount;
    private boolean pik;
    private boolean refusalAllowed;

    /**
     * Restricted constructor.
     */
    private Builder() {
      applyDefaults(this);
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(CommitmentAdjustment beanToCopy) {
      this.effectiveDate = beanToCopy.getEffectiveDate();
      this.amount = beanToCopy.getAmount();
      this.pik = beanToCopy.isPik();
      this.refusalAllowed = beanToCopy.isRefusalAllowed();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -930389515:  // effectiveDate
          return effectiveDate;
        case -1413853096:  // amount
          return amount;
        case 110994:  // pik
          return pik;
        case -1231792212:  // refusalAllowed
          return refusalAllowed;
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
        case 110994:  // pik
          this.pik = (Boolean) newValue;
          break;
        case -1231792212:  // refusalAllowed
          this.refusalAllowed = (Boolean) newValue;
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
    public CommitmentAdjustment build() {
      return new CommitmentAdjustment(
          effectiveDate,
          amount,
          pik,
          refusalAllowed);
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
     * Sets the contract event global amount.
     * @param amount  the new value
     * @return this, for chaining, not null
     */
    public Builder amount(CurrencyAmount amount) {
      this.amount = amount;
      return this;
    }

    /**
     * Sets flag indicating that this adjustment represents a capitalization from a PIK accrual.
     * @param pik  the new value
     * @return this, for chaining, not null
     */
    public Builder pik(boolean pik) {
      this.pik = pik;
      return this;
    }

    /**
     * Sets if true, indicates a non-prorata adjustment, e.g. new lender.
     * @param refusalAllowed  the new value
     * @return this, for chaining, not null
     */
    public Builder refusalAllowed(boolean refusalAllowed) {
      this.refusalAllowed = refusalAllowed;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("CommitmentAdjustment.Builder{");
      buf.append("effectiveDate").append('=').append(JodaBeanUtils.toString(effectiveDate)).append(',').append(' ');
      buf.append("amount").append('=').append(JodaBeanUtils.toString(amount)).append(',').append(' ');
      buf.append("pik").append('=').append(JodaBeanUtils.toString(pik)).append(',').append(' ');
      buf.append("refusalAllowed").append('=').append(JodaBeanUtils.toString(refusalAllowed));
      buf.append('}');
      return buf.toString();
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}
