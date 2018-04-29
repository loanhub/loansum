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

import java.time.LocalDate;

import org.joda.beans.ImmutableBean;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableList;
import com.opengamma.strata.basics.StandardId;
import com.opengamma.strata.collect.ArgChecker;
import org.joda.beans.MetaBean;
import org.joda.beans.gen.BeanDefinition;
import org.joda.beans.gen.ImmutableValidator;
import org.joda.beans.gen.PropertyDefinition;

/**
 * An accruing fee, e.g. commitment fee charged on unfunded.
 * <p>
 * An accrual defines specific interest accrual terms (start date,
 * end date, rate, day count convention) over a specific borrowed amount.
 * <p> * Over the course of a contract, multiple repayments of the contract amount
 * may occur. A borrowing always results in a new contract.
 * <p>
 * This is the standard representation but for usage within the calculation framework,
 * a contracted must be expanded into canonical form and prorated against a single trade.
 */
@BeanDefinition
public final class ProratedAccruingFee implements ImmutableBean {

  /**
   * The internal id of this fee.
   */
  @PropertyDefinition(validate = "notNull")
  private final StandardId id;

  /**
   * The fee type, for now just a free form string.
   */
  @PropertyDefinition(validate = "notNull")
  private final String type;

  /**
   * The share fee accrual representation.
   */
  @PropertyDefinition(validate = "notNull")
  private final ProratedAccrual accrual;

  /**
   * Generated accrual schedule.
   */
  @PropertyDefinition(validate = "", builderType = "List<? extends ProratedAccrual>")
  private final ImmutableList<ProratedAccrual> accrualSchedule;

  /**
   * The payment date of the contract.
   * <p>
   * Interest pays on this date. Usually identical to end date 
   * unless end date is a holiday. Payment date is given, not calculated.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate paymentDate;

  @ImmutableValidator
  private void validate() {
    ArgChecker.inOrderOrEqual(accrual.getEndDate(), paymentDate, "endDate", "paymentDate");
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code ProratedAccruingFee}.
   * @return the meta-bean, not null
   */
  public static ProratedAccruingFee.Meta meta() {
    return ProratedAccruingFee.Meta.INSTANCE;
  }

  static {
    MetaBean.register(ProratedAccruingFee.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ProratedAccruingFee.Builder builder() {
    return new ProratedAccruingFee.Builder();
  }

  private ProratedAccruingFee(
      StandardId id,
      String type,
      ProratedAccrual accrual,
      List<? extends ProratedAccrual> accrualSchedule,
      LocalDate paymentDate) {
    JodaBeanUtils.notNull(id, "id");
    JodaBeanUtils.notNull(type, "type");
    JodaBeanUtils.notNull(accrual, "accrual");
    JodaBeanUtils.notNull(paymentDate, "paymentDate");
    this.id = id;
    this.type = type;
    this.accrual = accrual;
    this.accrualSchedule = (accrualSchedule != null ? ImmutableList.copyOf(accrualSchedule) : null);
    this.paymentDate = paymentDate;
    validate();
  }

  @Override
  public ProratedAccruingFee.Meta metaBean() {
    return ProratedAccruingFee.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the internal id of this fee.
   * @return the value of the property, not null
   */
  public StandardId getId() {
    return id;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the fee type, for now just a free form string.
   * @return the value of the property, not null
   */
  public String getType() {
    return type;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the share fee accrual representation.
   * @return the value of the property, not null
   */
  public ProratedAccrual getAccrual() {
    return accrual;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets generated accrual schedule.
   * @return the value of the property
   */
  public ImmutableList<ProratedAccrual> getAccrualSchedule() {
    return accrualSchedule;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payment date of the contract.
   * <p>
   * Interest pays on this date. Usually identical to end date
   * unless end date is a holiday. Payment date is given, not calculated.
   * @return the value of the property, not null
   */
  public LocalDate getPaymentDate() {
    return paymentDate;
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
      ProratedAccruingFee other = (ProratedAccruingFee) obj;
      return JodaBeanUtils.equal(id, other.id) &&
          JodaBeanUtils.equal(type, other.type) &&
          JodaBeanUtils.equal(accrual, other.accrual) &&
          JodaBeanUtils.equal(accrualSchedule, other.accrualSchedule) &&
          JodaBeanUtils.equal(paymentDate, other.paymentDate);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(id);
    hash = hash * 31 + JodaBeanUtils.hashCode(type);
    hash = hash * 31 + JodaBeanUtils.hashCode(accrual);
    hash = hash * 31 + JodaBeanUtils.hashCode(accrualSchedule);
    hash = hash * 31 + JodaBeanUtils.hashCode(paymentDate);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("ProratedAccruingFee{");
    buf.append("id").append('=').append(id).append(',').append(' ');
    buf.append("type").append('=').append(type).append(',').append(' ');
    buf.append("accrual").append('=').append(accrual).append(',').append(' ');
    buf.append("accrualSchedule").append('=').append(accrualSchedule).append(',').append(' ');
    buf.append("paymentDate").append('=').append(JodaBeanUtils.toString(paymentDate));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ProratedAccruingFee}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code id} property.
     */
    private final MetaProperty<StandardId> _id = DirectMetaProperty.ofImmutable(
        this, "id", ProratedAccruingFee.class, StandardId.class);
    /**
     * The meta-property for the {@code type} property.
     */
    private final MetaProperty<String> _type = DirectMetaProperty.ofImmutable(
        this, "type", ProratedAccruingFee.class, String.class);
    /**
     * The meta-property for the {@code accrual} property.
     */
    private final MetaProperty<ProratedAccrual> _accrual = DirectMetaProperty.ofImmutable(
        this, "accrual", ProratedAccruingFee.class, ProratedAccrual.class);
    /**
     * The meta-property for the {@code accrualSchedule} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableList<ProratedAccrual>> _accrualSchedule = DirectMetaProperty.ofImmutable(
        this, "accrualSchedule", ProratedAccruingFee.class, (Class) ImmutableList.class);
    /**
     * The meta-property for the {@code paymentDate} property.
     */
    private final MetaProperty<LocalDate> _paymentDate = DirectMetaProperty.ofImmutable(
        this, "paymentDate", ProratedAccruingFee.class, LocalDate.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "type",
        "accrual",
        "accrualSchedule",
        "paymentDate");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 3575610:  // type
          return _type;
        case -1177229905:  // accrual
          return _accrual;
        case 304659814:  // accrualSchedule
          return _accrualSchedule;
        case -1540873516:  // paymentDate
          return _paymentDate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ProratedAccruingFee.Builder builder() {
      return new ProratedAccruingFee.Builder();
    }

    @Override
    public Class<? extends ProratedAccruingFee> beanType() {
      return ProratedAccruingFee.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code id} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> id() {
      return _id;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code accrual} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ProratedAccrual> accrual() {
      return _accrual;
    }

    /**
     * The meta-property for the {@code accrualSchedule} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ImmutableList<ProratedAccrual>> accrualSchedule() {
      return _accrualSchedule;
    }

    /**
     * The meta-property for the {@code paymentDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> paymentDate() {
      return _paymentDate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((ProratedAccruingFee) bean).getId();
        case 3575610:  // type
          return ((ProratedAccruingFee) bean).getType();
        case -1177229905:  // accrual
          return ((ProratedAccruingFee) bean).getAccrual();
        case 304659814:  // accrualSchedule
          return ((ProratedAccruingFee) bean).getAccrualSchedule();
        case -1540873516:  // paymentDate
          return ((ProratedAccruingFee) bean).getPaymentDate();
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
   * The bean-builder for {@code ProratedAccruingFee}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ProratedAccruingFee> {

    private StandardId id;
    private String type;
    private ProratedAccrual accrual;
    private List<? extends ProratedAccrual> accrualSchedule;
    private LocalDate paymentDate;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ProratedAccruingFee beanToCopy) {
      this.id = beanToCopy.getId();
      this.type = beanToCopy.getType();
      this.accrual = beanToCopy.getAccrual();
      this.accrualSchedule = beanToCopy.getAccrualSchedule();
      this.paymentDate = beanToCopy.getPaymentDate();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return id;
        case 3575610:  // type
          return type;
        case -1177229905:  // accrual
          return accrual;
        case 304659814:  // accrualSchedule
          return accrualSchedule;
        case -1540873516:  // paymentDate
          return paymentDate;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          this.id = (StandardId) newValue;
          break;
        case 3575610:  // type
          this.type = (String) newValue;
          break;
        case -1177229905:  // accrual
          this.accrual = (ProratedAccrual) newValue;
          break;
        case 304659814:  // accrualSchedule
          this.accrualSchedule = (List<? extends ProratedAccrual>) newValue;
          break;
        case -1540873516:  // paymentDate
          this.paymentDate = (LocalDate) newValue;
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
    public ProratedAccruingFee build() {
      return new ProratedAccruingFee(
          id,
          type,
          accrual,
          accrualSchedule,
          paymentDate);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the internal id of this fee.
     * @param id  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder id(StandardId id) {
      JodaBeanUtils.notNull(id, "id");
      this.id = id;
      return this;
    }

    /**
     * Sets the fee type, for now just a free form string.
     * @param type  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder type(String type) {
      JodaBeanUtils.notNull(type, "type");
      this.type = type;
      return this;
    }

    /**
     * Sets the share fee accrual representation.
     * @param accrual  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder accrual(ProratedAccrual accrual) {
      JodaBeanUtils.notNull(accrual, "accrual");
      this.accrual = accrual;
      return this;
    }

    /**
     * Sets generated accrual schedule.
     * @param accrualSchedule  the new value
     * @return this, for chaining, not null
     */
    public Builder accrualSchedule(List<? extends ProratedAccrual> accrualSchedule) {
      this.accrualSchedule = accrualSchedule;
      return this;
    }

    /**
     * Sets the {@code accrualSchedule} property in the builder
     * from an array of objects.
     * @param accrualSchedule  the new value
     * @return this, for chaining, not null
     */
    public Builder accrualSchedule(ProratedAccrual... accrualSchedule) {
      return accrualSchedule(ImmutableList.copyOf(accrualSchedule));
    }

    /**
     * Sets the payment date of the contract.
     * <p>
     * Interest pays on this date. Usually identical to end date
     * unless end date is a holiday. Payment date is given, not calculated.
     * @param paymentDate  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder paymentDate(LocalDate paymentDate) {
      JodaBeanUtils.notNull(paymentDate, "paymentDate");
      this.paymentDate = paymentDate;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("ProratedAccruingFee.Builder{");
      buf.append("id").append('=').append(JodaBeanUtils.toString(id)).append(',').append(' ');
      buf.append("type").append('=').append(JodaBeanUtils.toString(type)).append(',').append(' ');
      buf.append("accrual").append('=').append(JodaBeanUtils.toString(accrual)).append(',').append(' ');
      buf.append("accrualSchedule").append('=').append(JodaBeanUtils.toString(accrualSchedule)).append(',').append(' ');
      buf.append("paymentDate").append('=').append(JodaBeanUtils.toString(paymentDate));
      buf.append('}');
      return buf.toString();
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}
