package org.openmrs.validator;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.ConceptDatatype;
import org.openmrs.test.BaseContextSensitiveTest;
import org.openmrs.test.Verifies;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * Tests methods on the {@link ConceptDatatypeValidator} class.
 */
public class ConceptDatatypeValidatorTest extends BaseContextSensitiveTest {
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should fail validation if name is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName(null);
		cd.setDescription("some text");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cd.setName("");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cd.setName(" ");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should pass validation if description is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName("name");
		cd.setDescription(null);
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		cd.setDescription("");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		cd.setDescription(" ");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
	}
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should pass validation if all required fields have proper values", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllRequiredFieldsHaveProperValues() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName("name");
		cd.setDescription("some text");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should pass validation if field lengths are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName("name");
		cd.setDescription("some text");
		cd.setHl7Abbreviation("hl7");
		cd.setRetireReason("retireReason");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should fail validation if field lengths are not correct", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfFieldLengthsAreNotCorrect() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd
		        .setName("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		cd
		        .setDescription("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		cd.setHl7Abbreviation("hl7Abbreviation");
		cd
		        .setRetireReason("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("description"));
		Assert.assertTrue(errors.hasFieldErrors("hl7Abbreviation"));
		Assert.assertTrue(errors.hasFieldErrors("retireReason"));
	}
}
