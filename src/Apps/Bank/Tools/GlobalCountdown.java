package Apps.Bank.Tools;

import java.util.concurrent.TimeUnit;

public final class GlobalCountdown {
    private volatile long endNano = -1;

    public GlobalCountdown() {}

    // Startet diesen Countdown
    public synchronized void start(long duration, TimeUnit unit) {
        if (duration < 0) throw new IllegalArgumentException("duration < 0");
        endNano = System.nanoTime() + unit.toNanos(duration);
    }

    public long getRemainingSeconds() {
        long rem = getRemainingNanos();
        return rem / 1_000_000_000L;
    }

    public long getRemainingMillis() {
        long rem = getRemainingNanos();
        return rem / 1_000_000L;
    }

    public long getRemainingNanos() {
        long end = endNano;
        if (end < 0) return 0;
        long rem = end - System.nanoTime();
        return Math.max(0, rem);
    }

    public boolean isFinished() {
        return getRemainingNanos() == 0;
    }

    public synchronized void reset() {
        endNano = -1;
    }

    public boolean isStarted() {
        return endNano >= 0;
    }
}