package com.dev.spring.validation;

import javax.validation.groups.Default;

public interface ValidationGroups {
    interface Post extends Default { 
    }
    interface Put extends Default {
    }
}
