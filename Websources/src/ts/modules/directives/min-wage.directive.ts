import {ValidatorFn, AbstractControl} from "@angular/forms";
/**
 * Created by boebel on 26.01.2017.
 */
export function maximumWageValidator(nameRe): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} => {
        const wage = control.value;
        return wage < 1800 ? {'toohighwage': {wage}} : null;
    };
}