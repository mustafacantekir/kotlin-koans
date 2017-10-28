package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate = this.addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.times)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(times: Int) = RepeatedTimeInterval(this, times)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterator<MyDate> {
    private var now = start
    override fun hasNext(): Boolean = now <= endInclusive

    override fun next(): MyDate {
        val result = now
        now = now.addTimeIntervals(TimeInterval.DAY, 1)
        return result
    }
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)
