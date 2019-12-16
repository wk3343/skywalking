package org.apache.skywalking.apm.agent.core.sampling;

import org.apache.skywalking.apm.agent.core.boot.BootService;
import org.apache.skywalking.apm.agent.core.boot.DefaultImplementor;
import org.apache.skywalking.apm.agent.core.conf.Config;

import java.util.Random;

@DefaultImplementor
public class ThrottlingService implements BootService {

    private Random rand = new Random();

    private int throttlingRate;

    @Override
    public void prepare() throws Throwable {

    }

    @Override
    public void boot() throws Throwable {
        throttlingRate = Config.Agent.THROTTLING_RATE;
        if (throttlingRate <= 0 || throttlingRate > 10000) {
            throttlingRate = 10000;
        }
    }

    @Override
    public void onComplete() throws Throwable {

    }

    @Override
    public void shutdown() throws Throwable {

    }

    public boolean tryThrottling() {
        return rand.nextInt(10000) >= throttlingRate;
    }
}
