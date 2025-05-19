import React, { useState, useEffect } from "react";

const ProbaForm = ({ onSave, selectedProba, clearSelection }) => {
    const [nume, setNume] = useState('');
    const [categorie, setCategorie] = useState('');
    const [durata, setDurata] = useState('');

    useEffect(() => {
        if (selectedProba) {
            setNume(selectedProba.nume);
            setCategorie(selectedProba.categorie);
            setDurata(selectedProba.durata ?? '');
        }
    }, [selectedProba]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const updated = {
            id: selectedProba?.id,
            nume,
            categorie,
            durata: durata ? parseInt(durata) : null,
        };
        onSave(updated);
        setNume(''); setCategorie(''); setDurata('');
        clearSelection();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input placeholder="Nume" value={nume} onChange={e => setNume(e.target.value)} />
            <input placeholder="Categorie" value={categorie} onChange={e => setCategorie(e.target.value)} />
            <input placeholder="Durata (sec)" value={durata} onChange={e => setDurata(e.target.value)} />
            <button type="submit">{selectedProba ? "Modifică" : "Adaugă"}</button>
        </form>
    );
};

export default ProbaForm;