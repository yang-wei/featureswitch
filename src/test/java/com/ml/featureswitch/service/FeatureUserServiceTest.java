package com.ml.featureswitch.service;

import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.model.User;
import com.ml.featureswitch.repository.FeatureRepository;
import com.ml.featureswitch.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class FeatureUserServiceTest{

    @Mock
    FeatureRepository featureRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    FeatureUserService service;

    @Test
    public void return_false_when_switch_on_enabled_toggle() {
        Feature feature = new Feature("instacash");
        User user = new User("user1@gmail.com");
        feature.addUser(user);
        boolean toggled = service.toggle(feature, user, true);
        Assert.isTrue(!toggled, "will not toggle since it's on");
    }

    @Test
    public void return_false_when_switch_off_disabled_toggle() {
        Feature feature = new Feature("instacash");
        User user = new User("user1@gmail.com");
        boolean toggled = service.toggle(feature, user, false);
        Assert.isTrue(!toggled, "will not toggle since it's off");
    }

    @Test
    public void return_true_when_switch_on_disabled_toggle() {
        Feature feature = new Feature("instacash");
        User user = new User("user1@gmail.com");
        boolean toggled = service.toggle(feature, user, true);
        Assert.isTrue(toggled, "will toggle since it's off");
    }

    @Test
    public void return_true_when_switch_off_enabled_toggle() {
        Feature feature = new Feature("instacash");
        User user = new User("user1@gmail.com");
        feature.addUser(user);
        boolean toggled = service.toggle(feature, user, false);
        Assert.isTrue(toggled, "will toggle since it's on");
    }
}
