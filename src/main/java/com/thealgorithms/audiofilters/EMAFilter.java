package com.thealgorithms.audiofilters;

/**
 * Exponential Moving Average (EMA) Filter for smoothing audio signals.
 * 指数平均移动滤波器，用于平滑音频信号，减少快速波动（用于信号处理）
 * EMAt = α×Xt + (1−α)×EMAt−1
 * 如果 alpha 小，EMA 变化更慢，平滑效果更强
 * <p>This filter applies an exponential moving average to a sequence of audio
 * signal values, making it useful for smoothing out rapid fluctuations.
 * The smoothing factor (alpha) controls the degree of smoothing.
 *
 * <p>Based on the definition from
 * <a href="https://en.wikipedia.org/wiki/Moving_average">Wikipedia link</a>.
 */
public class EMAFilter {
    private final double alpha; // 平滑因子，控制平滑程度
    private double emaValue; // 存储当前 EMA 计算值
    /**
     * Constructs an EMA filter with a given smoothing factor.
     *
     * @param alpha Smoothing factor (0 < alpha <= 1)
     * @throws IllegalArgumentException if alpha is not in (0, 1]
     */
    public EMAFilter(double alpha) {
        if (alpha <= 0 || alpha > 1) {
            throw new IllegalArgumentException("Alpha must be between 0 and 1.");
        }
        this.alpha = alpha;
        this.emaValue = 0.0;
    }
    /**
     * Applies the EMA filter to an audio signal array.
     *
     * @param audioSignal Array of audio samples to process
     * @return Array of processed (smoothed) samples
     */
    public double[] apply(double[] audioSignal) {
        if (audioSignal.length == 0) {
            return new double[0];
        }
        double[] emaSignal = new double[audioSignal.length];
        emaValue = audioSignal[0];
        emaSignal[0] = emaValue;
        for (int i = 1; i < audioSignal.length; i++) {
            emaValue = alpha * audioSignal[i] + (1 - alpha) * emaValue;
            emaSignal[i] = emaValue;
        }
        return emaSignal;
    }
}
