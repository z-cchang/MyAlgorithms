package Algorithms.Alignment.Blast;

import IO.FastaFileReader;
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Blast is able to ")
class BLASTSpec {
    DNASequence seq1;
    DNASequence seq2;
    BLAST blast;
    @BeforeEach
    void init() throws CompoundNotFoundException {
        seq1 = new DNASequence("ATGCACGTC");
        seq2 = new DNASequence("ACG");
        blast = new BLASTP();
    }
    @DisplayName("find subSequences.")
    @Test
    void indexOfTest() throws CompoundNotFoundException {
        DNASequence pat = blast.subSeq(seq1,5,8);
        int res = blast.indexOf(seq1,pat,1);
        assertEquals(5,res);

//        -------------------------------
        pat = new DNASequence("AAA");
        res = blast.indexOf(seq1,pat,2);
        assertEquals(-1,res);
    }
    @DisplayName("sign the subSequences.")
    @Test
    void findSub() throws CompoundNotFoundException {
        blast.subSeq(seq1,1,2);
        assertEquals(1,seq1.getBioBegin());
        assertEquals(2, seq1.getBioEnd());
    }

    @DisplayName("give the right evalue ")
    @Test
    void E_value() throws Exception {
        FastaFileReader reader = new FastaFileReader("src/main/java/resources/coronavirus2019.faa","src/main/java/resources/sars2003.faa");
        ProteinSequence sequence1 = reader.getSeq_1().get("QHD43415.1 orf1ab polyprotein (pp1ab) [Wuhan seafood market pneumonia virus]");
        ProteinSequence sequence2 = reader.getSeq_2().get("AAP41037.1 spike glycoprotein [Severe acute respiratory syndrome-related coronavirus]");
        SubstitutionMatrix<AminoAcidCompound> matrix = SubstitutionMatrixHelper.getBlosum62();
        SequencePair<ProteinSequence, AminoAcidCompound> pair = Alignments.getPairwiseAlignment(sequence1,sequence2, Alignments.PairwiseSequenceAlignerType.LOCAL,new SimpleGapPenalty(),matrix);
        System.out.println(pair.getPercentageOfIdentity(true));
    }
    @DisplayName("give the right evalue ")
    @Test
    void E_value_2() throws Exception {
        FastaFileReader reader = new FastaFileReader("src/main/java/resources/coronavirus2019.faa","src/main/java/resources/sars2003.faa");
        ProteinSequence sequence1 = reader.getSeq_1().get("QHD43415.1 orf1ab polyprotein (pp1ab) [Wuhan seafood market pneumonia virus]");
        ProteinSequence sequence2 = reader.getSeq_2().get("AAP41038.1 Orf3 [Severe acute respiratory syndrome-related coronavirus]");
        System.out.println(blast.align(sequence1,sequence2));
    }


}