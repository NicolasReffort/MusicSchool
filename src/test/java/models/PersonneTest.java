package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 */
 class PersonneTest
 
{   
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @ParameterizedTest
    @ValueSource ( strings =  { "M", "", "0" } )
    
    public void testerPrenom(String mauvaisPrenom ){
        
        Personne personneLambda = new Personne("Rrrrr", mauvaisPrenom, 37);
        Set<ConstraintViolation<Personne>> violations = validator.validate(personneLambda);

        // for (ConstraintViolation<Personne> violation : violations) {
        //     JOptionPane.showMessageDialog(null, violation.getInvalidValue());
        // }

        assertFalse(violations.isEmpty());
                                
    } 

    @ParameterizedTest
    @ValueSource ( strings =  { "",  " Rhoshandiatellyneshiaunneveshenkescianneshaimondrischlyndasaccarnae renquellenendrasamecashaunettethalemeicoleshiwhalhinive’onchellecaundenesh eaalausondrilynnejeanetrimyranaekuesaundrilynnezekeriakenvaunetradevonneya vondalatarneskcaevontaepreonkeinesceellaviavelzadawnefriendsettajessicanneles ciajoyvaelloydietteyvettesparklenesceaundrieaquenttaekatilyaevea’shauwneorali aevaekizzieshiyjuanewandalecciannereneitheliapreciousnesceverroneccalovelia tyronevekacarrionnehenriettaescecleonpatrarutheliacharsalynnmeokcamonaeloies alynnecsiannemerciadellesciaustillaparissalondonveshadenequamonecaalexetiozetiaquaniaenglaundneshiafrancethosharomeshaunnehawaineakowethauandavernellchishankcarl naaddoneillesciachristondrafawndrealaotrelleoctavionnemiariasarahtashabnequck gailenaxeteshiataharadaponsadeloriakoentescacraigneckadellanierstellavonnemyia angoneshiadianacorvettinagodtawndrashirlenescekilokoneyasharrontannamyantoniaaquin ettesequioadaurilessiaquatandamerceddiamaebellecescajamesauwnneltom ecapolotyoajohny aetheodoradilcyana" } )
    
    public void testerNoms(String mauvaisNom ){
        
        Personne personneLambda = new Personne(mauvaisNom, "Nicolas" , 37);
        Set<ConstraintViolation<Personne>> violations = validator.validate(personneLambda);

        // for (ConstraintViolation<Personne> violation : violations) {
        //     JOptionPane.showMessageDialog(null, violation.getInvalidValue());
        // }
        assertFalse(violations.isEmpty());                                
    } 

    @ParameterizedTest
    @ValueSource(strings = { "Da", "Daniel", "Daniel" })

    public void verifierPrenom(String bonPrenom) {

        Personne personneLambda = new Personne("Rrrrr", bonPrenom, 37);
        Set<ConstraintViolation<Personne>> violations = validator.validate(personneLambda);

        // for (ConstraintViolation<Personne> violation : violations) {
        // JOptionPane.showMessageDialog(null, violation.getInvalidValue());
        // }

        assertTrue(violations.isEmpty());

    }

    @ParameterizedTest
    @ValueSource(strings = { "B a ", "Ba La Voine", "Balavoine" })

    public void verifierNom(String bonNom) {

        Personne personneLambda = new Personne("Rrrrr", bonNom, 37);
        Set<ConstraintViolation<Personne>> violations = validator.validate(personneLambda);
        assertTrue(violations.isEmpty());

    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 133,  })

    public void testerAge(Integer mauvaisAge) {

        Personne personneLambda = new Personne("Rrrrr", "Rrrrr", mauvaisAge);
        Set<ConstraintViolation<Personne>> violations = validator.validate(personneLambda);
        assertFalse(violations.isEmpty());
    }

}

