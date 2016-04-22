package com.umitates.cl.core.entity;

public abstract class AbstractEntity implements IEntity {

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (this == object) {
			return true;
		}
		
		if (this.getClass().equals(object.getClass())) {
			AbstractEntity entity = (AbstractEntity) object;
			return this.getId().equals(entity.getId());
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
