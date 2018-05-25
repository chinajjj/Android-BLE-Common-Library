package no.nordicsemi.android.ble.common.profile.bp;

@SuppressWarnings("WeakerAccess")
public interface BloodPressureTypes {
	int UNIT_mmHg = 0;
	int UNIT_kPa = 1;

	class BPMStatus {
		public final boolean bodyMovementDetected;
		public final boolean cuffTooLose;
		public final boolean irregularPulseDetected;
		public final boolean pulseRateInRange;
		public final boolean pulseRateExceedsUpperLimit;
		public final boolean pulseRateIsLessThenLowerLimit;
		public final boolean improperMeasurementPosition;
		public final int value;

		public BPMStatus(final int status) {
			this.value = status;

			bodyMovementDetected = (status & 0x01) != 0;
			cuffTooLose = (status & 0x02) != 0;
			irregularPulseDetected = (status & 0x04) != 0;
			pulseRateInRange = (status & 0x18) >> 3 == 0;
			pulseRateExceedsUpperLimit = (status & 0x18) >> 3 == 1;
			pulseRateIsLessThenLowerLimit = (status & 0x18) >> 3 == 2;
			improperMeasurementPosition = (status & 0x20) != 0;
		}
	}

	/**
	 * Converts the value provided in given unit to mmHg.
	 * If the unit is already {@link #UNIT_mmHg} it will be returned as is.
	 *
	 * @param value the pressure value in given unit.
	 * @param unit the unit of the value ({@link #UNIT_mmHg} or {@link #UNIT_kPa}).
	 * @return Value in mmHg.
	 */
	static float toMmHg(final float value, final int unit) {
		if (unit == UNIT_mmHg) {
			return value;
		} else {
			return value / 0.133322387415f;
		}
	}

	/**
	 * Converts the value provided in given unit to kPa.
	 * If the unit is already {@link #UNIT_kPa} it will be returned as is.
	 *
	 * @param value the pressure value in given unit.
	 * @param unit the unit of the value ({@link #UNIT_mmHg} or {@link #UNIT_kPa}).
	 * @return Value in kPa.
	 */
	static float toKPa(final float value, final int unit) {
		if (unit == UNIT_kPa) {
			return value;
		} else {
			return value * 0.133322387415f;
		}
	}
}
