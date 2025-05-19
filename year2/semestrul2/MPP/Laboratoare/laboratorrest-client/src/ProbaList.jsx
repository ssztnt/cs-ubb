import React, { useEffect, useState } from "react";
import ProbaForm from "./ProbaForm";
import { connectToWebSocket } from './websocket';

const ProbaList = () => {
    const [probes, setProbes] = useState([]);
    const [error, setError] = useState(null);
    const [selectedProba, setSelectedProba] = useState(null);

    const fetchAll = () => {
        fetch("http://localhost:8080/api/probe")
            .then((res) => res.json())
            .then(setProbes)
            .catch(() => setError("Load failed"));
    };

    useEffect(() => {
        fetchAll();

        connectToWebSocket((updatedList) => {
            setProbes(updatedList); // 🔄 Actualizare live pe WebSocket
        });
    }, []);

    const handleSave = (proba) => {
        const method = proba.id ? "PUT" : "POST";
        const url = proba.id
            ? `http://localhost:8080/api/probe/${proba.id}`
            : "http://localhost:8080/api/probe";

        fetch(url, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(proba),
        })
            .then(() => fetchAll())
            .catch(() => setError("Save failed"));
    };

    const handleDelete = (id) => {
        fetch(`http://localhost:8080/api/probe/${id}`, { method: "DELETE" })
            .then(() => fetchAll())
            .catch(() => setError("Delete failed"));
    };

    return (
        <div>
            <h2>Lista probelor</h2>
            {error && <p style={{ color: "red" }}>Eroare: {error}</p>}
            <ProbaForm
                onSave={handleSave}
                selectedProba={selectedProba}
                clearSelection={() => setSelectedProba(null)}
            />
            {probes.length === 0 ? (
                <p>Nu există probe.</p>
            ) : (
                <ul>
                    {probes.map((p) => (
                        <li key={p.id}>
                            <strong>{p.nume}</strong> — {p.categorie} {p.durata && <>({p.durata} sec)</>}
                            <button onClick={() => setSelectedProba(p)}>✏️</button>
                            <button onClick={() => handleDelete(p.id)}>🗑️</button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default ProbaList;