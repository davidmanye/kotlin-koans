package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return Comparator
                .comparingInt<MyDate> { it.year }
                .thenComparingInt { it.month }
                .thenComparingInt { it.dayOfMonth }
                .compare(this, other)
    }

    operator fun plus(interval: TimeInterval): MyDate {

        return this.addTimeIntervals(interval, 1)
    }

    operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
        return this.addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(num: Int): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, num)
    }
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> {
        return object: Iterator<MyDate> {

            var current = start

            override fun hasNext(): Boolean {
                return current <= endInclusive
            }

            override fun next(): MyDate {
                val ret = current
                this.current = current.nextDay()
                return ret
            }
        }
    }

    operator fun contains(d: MyDate): Boolean {
        return start < d && d <= endInclusive
    }
}
