"use strict";
/**
 * Created by boebel on 26.01.2017.
 */
function maximumWageValidator(nameRe) {
    return (control) => {
        const wage = control.value;
        return wage < 1800 ? { 'toohighwage': { wage } } : null;
    };
}
exports.maximumWageValidator = maximumWageValidator;
//# sourceMappingURL=min-wage.directive.js.map